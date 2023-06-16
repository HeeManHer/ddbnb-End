package com.nasigolang.ddbnb.jwt;


import com.nasigolang.ddbnb.exception.TokenException;
import com.nasigolang.ddbnb.member.dto.MemberDTO;

import com.nasigolang.ddbnb.member.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import com.nasigolang.ddbnb.login.dto.AccessTokenDTO;

@Component
public class TokenProvider {

	private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "Auth";
	private static final String BEARER_TYPE = "bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 21599;    // 30분 정도가 이상적이나 Redis 사용 이전까지는 카카오 액세스 토큰과 시간을 같게 함

	private final UserDetailsService userDetailsService;

	private final Key key;

	public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public AccessTokenDTO generateMemberTokenDTO(Member foundmember) {
		log.info("[TokenProvider] generateTokenDto Start ===================================");

		Claims claims = Jwts
				.claims()
				.setSubject(String.valueOf(foundmember.getMemberId()));
		long now = (new Date()).getTime();

		// Access Token 생성
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
		String jwtToken = Jwts.builder()
				.setClaims(claims)
				.setExpiration(accessTokenExpiresIn)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		System.out.println(jwtToken);
		return new AccessTokenDTO(BEARER_TYPE, foundmember.getMemberId(), jwtToken,
				accessTokenExpiresIn.getTime());
	}

	public Authentication getAuthentication(String accessToken) {

		/* 토큰 복호화 */
		Claims claims = parseClaims(accessToken);

		System.out.println("claims = " + claims);

		/* 클레임에서 권한 정보 가져오기 */
//		Collection<? extends GrantedAuthority> authorities =
//				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//						.map(SimpleGrantedAuthority::new)
//						.collect(Collectors.toList());
//		log.info("[TokenProvider] authorities : {}", authorities);
		// UserDetails 객체를 만들어서 Authentication 리턴
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(accessToken));

		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//		return null;
	}

	private String getUserId(String accessToken) {
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken)
				.getBody()
				.getSubject();
	}

	public boolean validateToken(String token) throws ExpiredJwtException {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("[TokenProvider] Malformed JWT Sign");
			throw new TokenException("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("[TokenProvider] Expired JWT Token");
			throw new TokenException("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("[TokenProvider] Unsupported JWT token");
			throw new TokenException("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("[TokenProvider] JWT Token Illegal");
			throw new TokenException("JWT 토큰이 잘못되었습니다.");
		}
	}


	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
