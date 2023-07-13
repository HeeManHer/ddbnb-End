package com.nasigolang.ddbnb.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasigolang.ddbnb.login.dto.*;
import com.nasigolang.ddbnb.member.dto.MemberDTO;
import com.nasigolang.ddbnb.member.entity.Member;
import com.nasigolang.ddbnb.member.service.MemberService;
import com.nasigolang.ddbnb.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Service
public class LoginService {
    private final MemberService memberService;
    private final FileUploadUtils fileUploadUtils;

    @Value("${oauth.Kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${oauth.naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${oauth.naver.secret}")
    private String NAVER_SECRET;


    public LoginService(MemberService memberService, FileUploadUtils fileUploadUtils) {
        this.memberService = memberService;
        this.fileUploadUtils = fileUploadUtils;
    }

    public KakaoAcessTokenDTO getAccessToken(String code) {

        RestTemplate rt = new RestTemplate();
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("redirect_uri", "https://www.ddbnb.site/kakao/callback");
//        params.add("redirect_uri", "http://localhost:3000/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
                kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoAcessTokenDTO kakaoToken = null;
        try {
            kakaoToken = objectMapper.readValue(accessTokenResponse.getBody(), KakaoAcessTokenDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kakaoToken.setLoginType("kakao");

        return kakaoToken;
    }

    public KakaoProfileDTO findKakaoProfile(String accessToken) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> kakaoProfileResponse = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                kakaoProfileRequest, String.class);

        KakaoProfileDTO kakaoProfileDTO = new KakaoProfileDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            kakaoProfileDTO = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfileDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return kakaoProfileDTO;
    }

    public String findProfileImage(String imageUrl) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");

        HttpEntity<MultiValueMap<String, String>> profileImageRequest = new HttpEntity<>(headers);

        ResponseEntity<byte[]> profileImageResponse = rt.exchange(
                "https://api.dicebear.com/6.x/thumbs/png?seed=" + imageUrl, HttpMethod.GET, profileImageRequest,
                byte[].class);

        byte[] profileImage = profileImageResponse.getBody();

        String replaceFileName;

        try {
            replaceFileName = fileUploadUtils.saveFile(profileImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return replaceFileName;
    }

    @Transactional
    public void getJwtToken(KakaoAcessTokenDTO kakaoToken) {

        KakaoProfileDTO kakaoProfileDTO = findKakaoProfile(kakaoToken.getAccess_token());

        /* 해당 유저의 가입 이력이 없을 경우 */
        if (memberService.findBySocialId("KAKAO", String.valueOf(kakaoProfileDTO.getId())) == null) {
            MemberDTO newMember = new MemberDTO();

            newMember.setSocialLogin("KAKAO");
            newMember.setSocialId(String.valueOf(kakaoProfileDTO.getId()));

            newMember.setRefreshToken(kakaoToken.getRefresh_token());
            newMember.setRefreshTokenExpireDate(kakaoToken.getRefresh_token_expires_in() + System.currentTimeMillis());

            newMember.setAccessToken(kakaoToken.getAccess_token());
            newMember.setAccessTokenExpireDate(kakaoToken.getExpires_in() + System.currentTimeMillis());

            newMember.setStatus("정상");
            newMember.setProfileImage(findProfileImage(newMember.getSocialId()));

            newMember.setSignDate(LocalDate.now());
            newMember.setLastVisitDate(LocalDate.now());

            if (kakaoProfileDTO.getKakao_account().getGender() != null) {
                newMember.setGender(kakaoProfileDTO.getKakao_account().getGender());
            }

            newMember.setStarPoint("0");
            memberService.registNewUser(newMember);
        }

        /* 소셜 아이디로 멤버가 있는지 조회해 가져옴 */
        Member foundmember = memberService.findBySocialId("KAKAO", String.valueOf(kakaoProfileDTO.getId()));
        kakaoToken.setMemberId(foundmember.getMemberId());

        /* 액세스토큰, 리프레시 토큰 업데이트 */
        foundmember.setRefreshToken(kakaoToken.getRefresh_token());
        foundmember.setAccessToken(kakaoToken.getAccess_token());
        foundmember.setRefreshTokenExpireDate(kakaoToken.getRefresh_token_expires_in() + System.currentTimeMillis());
        foundmember.setAccessTokenExpireDate(kakaoToken.getExpires_in() + System.currentTimeMillis());
        foundmember.setLastVisitDate(LocalDate.now());

        Date accessExpireDate = new Date(foundmember.getAccessTokenExpireDate());

        if (accessExpireDate.before(new Date(System.currentTimeMillis()))) {

            RenewTokenDTO renewedToken = renewKakaoToken(foundmember);

            if (renewedToken.getRefresh_token() != null) {

                foundmember.setRefreshToken(renewedToken.getRefresh_token());
                foundmember.setRefreshTokenExpireDate(
                        renewedToken.getRefresh_token_expires_in() + System.currentTimeMillis());
            }

            foundmember.setAccessToken(renewedToken.getAccess_token());
            foundmember.setAccessTokenExpireDate(renewedToken.getExpires_in() + System.currentTimeMillis());

        }
    }


    public RenewTokenDTO renewKakaoToken(Member foundMember) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("refresh_token", foundMember.getRefreshToken());


        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> renewTokenResponse = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
                kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        RenewTokenDTO renewToken = null;
        try {
            renewToken = objectMapper.readValue(renewTokenResponse.getBody(), RenewTokenDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return renewToken;
    }

    public boolean kakaoLogout(String accessToken) {

        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> kakaoLogoutResponse = rt.exchange("https://kapi.kakao.com/v1/user/logout",
                //                "https://kapi.kakao.com/v1/user/unlink",
                HttpMethod.POST, kakaoLogoutRequest, String.class);

        return kakaoLogoutResponse.getStatusCode().is2xxSuccessful();
    }


    public NaverAccessTokenDTO getNaverAccessToken(String code, String state) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", NAVER_SECRET);
        params.add("code", code);
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = rt.exchange("https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST, naverTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        NaverAccessTokenDTO naverAccessToken = null;
        try {
            naverAccessToken = objectMapper.readValue(accessTokenResponse.getBody(), NaverAccessTokenDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        naverAccessToken.setLoginType("naver");

        return naverAccessToken;
    }

    @Transactional
    public void getJwtToken(NaverAccessTokenDTO naverAccessToken) {

        NaverProfileDTO naverProfileDTO = findNaverProfile(naverAccessToken.getAccess_token());

        /* 해당 유저의 가입 이력이 없을 경우 */
        if (memberService.findBySocialId("NAVER", naverProfileDTO.getResponse().getId()) == null) {

            MemberDTO newMember = new MemberDTO();

            newMember.setSocialLogin("NAVER");
            newMember.setSocialId(naverProfileDTO.getResponse().getId());
            newMember.setRefreshToken(naverAccessToken.getRefresh_token());
            newMember.setAccessToken(naverAccessToken.getAccess_token());
            newMember.setRefreshTokenExpireDate((1000 * 60 * 60 * 6) + System.currentTimeMillis());
            newMember.setAccessTokenExpireDate(naverAccessToken.getExpires_in() + System.currentTimeMillis());
            newMember.setSignDate(LocalDate.now());
            newMember.setLastVisitDate(LocalDate.now());
            newMember.setProfileImage("https://api.dicebear.com/6.x/thumbs/png?seed=" + newMember.getSocialId()
                    .split("@")[0]);

            if (naverProfileDTO.getResponse().getGender() != null) {
                newMember.setGender(naverProfileDTO.getResponse().getGender());
            }

            memberService.registNewUser(newMember);
        }

        /* 소셜 아이디로 멤버가 있는지 조회해 가져옴 */
        Member foundmember = memberService.findBySocialId("NAVER", naverProfileDTO.getResponse().getId());

        naverAccessToken.setMemberId(foundmember.getMemberId());
        foundmember.setRefreshToken(naverAccessToken.getRefresh_token());
        foundmember.setAccessToken(naverAccessToken.getAccess_token());
        foundmember.setRefreshTokenExpireDate(
                naverAccessToken.getRefresh_token_expires_in() + System.currentTimeMillis());
        foundmember.setAccessTokenExpireDate(naverAccessToken.getExpires_in() + System.currentTimeMillis());


        Date accessExpireDate = new Date(foundmember.getAccessTokenExpireDate());

        if (accessExpireDate.before(new Date())) {

            RenewTokenDTO renewedToken = renewNaverToken(foundmember);

            if (renewedToken.getRefresh_token() != null) {

                foundmember.setRefreshToken(renewedToken.getRefresh_token());
                foundmember.setRefreshTokenExpireDate(
                        renewedToken.getRefresh_token_expires_in() + System.currentTimeMillis());
            }

            foundmember.setAccessToken(renewedToken.getAccess_token());
            foundmember.setAccessTokenExpireDate(renewedToken.getExpires_in() + System.currentTimeMillis());
        }
    }

    public NaverProfileDTO findNaverProfile(String accessToken) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> naverProfileResponse = rt.exchange("https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST, naverProfileRequest, String.class);

        NaverProfileDTO naverProfileDTO = new NaverProfileDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            naverProfileDTO = objectMapper.readValue(naverProfileResponse.getBody(), NaverProfileDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return naverProfileDTO;
    }

    public RenewTokenDTO renewNaverToken(Member foundMember) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", NAVER_SECRET);
        params.add("refresh_token", foundMember.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> naverTokenResponses = rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.GET,
                naverTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        RenewTokenDTO renewToken = null;
        try {
            renewToken = objectMapper.readValue(naverTokenResponses.getBody(), RenewTokenDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return renewToken;
    }

    public boolean naverLogout(String accessToken) {

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> naverLogoutRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> naverLogoutResponse = rt.exchange("https://nid.naver.com/nidlogin.logout",
                HttpMethod.POST, naverLogoutRequest, String.class);

        return naverLogoutResponse.getStatusCode().is2xxSuccessful();
    }
}
