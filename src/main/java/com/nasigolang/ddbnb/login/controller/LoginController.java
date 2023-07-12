package com.nasigolang.ddbnb.login.controller;


import com.nasigolang.ddbnb.common.ResponseDTO;
import com.nasigolang.ddbnb.login.dto.KakaoAcessTokenDTO;
import com.nasigolang.ddbnb.login.dto.NaverAccessTokenDTO;
import com.nasigolang.ddbnb.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "로그인 내부 API")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PreAuthorize("permitAll()")
    @ApiOperation(value = "카카오 인가 코드 받아와서 액세스 토큰 발급")
    @PostMapping("/kakaocode")
    public ResponseEntity<?> getKakaoCode(@RequestBody Map<String, String> code) {
        /* 인가 코드로 액세스 토큰 발급 */
        KakaoAcessTokenDTO kakaoToken = loginService.getAccessToken(code.get("code"));

        /* 액세스 토큰으로 DB 저장or 확인 후 JWT 생성 */
        loginService.getJwtToken(kakaoToken);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", kakaoToken);

        /* JWT와 응답 결과를 프론트에 전달*/
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", responseMap));
    }

    @PostMapping("/kakaologout")
    public ResponseEntity<?> kakaoLogout(@RequestHeader("Authorization") String accessToken) {
        /* 카카오 로그아웃 API 호출 */
        boolean logoutSuccess = loginService.kakaoLogout(accessToken);

        if (logoutSuccess) {
            /* 로그아웃 성공 처리 */
            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "로그아웃 성공", null));
        } else {
            /* 로그아웃 실패 처리 */
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "로그아웃 실패", null));
        }
    }


    @ApiOperation(value = "jwt 액세스 토큰 만료되어 재발급")
    @PostMapping("/renew")
    public ResponseEntity<?> renewAccessToken(@RequestHeader(value = "Auth") String auth) {

        return null;
    }

    @PreAuthorize("permitAll()")
    @ApiOperation(value = "네이버 인가 코드 받아와서 액세스 토큰 발급")
    @PostMapping("/navercode")
    public ResponseEntity<?> getNaverCode(@RequestBody Map<String, String> codeAndState) {
        /* 인가 코드로 액세스 토큰 발급 */
        NaverAccessTokenDTO naverAccessToken = loginService.getNaverAccessToken(codeAndState.get("code"),
                                                                                codeAndState.get("state"));

        /* 액세스 토큰으로 DB 저장or 확인 후 JWT 생성 */
        loginService.getJwtToken(naverAccessToken);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", naverAccessToken);

        /* JWT와 응답 결과를 프론트에 전달*/
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공", responseMap));
    }

    @PostMapping("/naverlogout")
    public ResponseEntity<?> naverLogout(@RequestHeader("Authorization") String accessToken) {
        /* 카카오 로그아웃 API 호출 */
        boolean logoutSuccess = loginService.naverLogout(accessToken);

        if (logoutSuccess) {
            /* 로그아웃 성공 처리 */
            return ResponseEntity
                    .ok()
                    .body(new ResponseDTO(HttpStatus.OK, "로그아웃 성공", null));
        } else {
            /* 로그아웃 실패 처리 */
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST, "로그아웃 실패", null));
        }
    }
}