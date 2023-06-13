package com.nasigolang.ddbnb.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasigolang.ddbnb.exception.ApiExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(OncePerRequestFilter.class);

	public static final String AUTHORIZATION_HEADER = "Auth";
	public static final String BEARER_PREFIX = "bearer";

	private final TokenProvider tokenProvider;

	private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/api/v1/login/**");

	public JwtFilter(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}

	// 실제 필터링 로직은 doFilterInternal 에 들어감
	// JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		System.out.println("호출됨????");

		try {
			/* 1. Request Header 토큰 꺼내기 */
			String jwtHeader = resolveToken(request);
			log.info("[JwtFilter] jwtHeader : {}", jwtHeader);

			// 2. validateToken 으로 토큰 유효성 검사
			// 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
			if (StringUtils.hasText(jwtHeader) && tokenProvider.validateToken(jwtHeader)) {
//				Authentication authentication = tokenProvider.getAuthentication(jwtHeader);
//				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		} catch (RuntimeException e) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			ApiExceptionDto errorResponse = new ApiExceptionDto(HttpStatus.UNAUTHORIZED, e.getMessage());


			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(convertObjectToJson(errorResponse));
		}
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}


	private String resolveToken(HttpServletRequest request) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();

		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		Map<String, String> tokenToMap = objectMapper.readValue(bearerToken, Map.class);

		if(StringUtils.hasText(tokenToMap.get("accessToken")) && (tokenToMap.get("grantType").equals(BEARER_PREFIX))) {
			System.out.println("yes");
			return tokenToMap.get("accessToken");
		}

		return null;
//		return bearerToken;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		String method = request.getMethod();

		if("/api/v1/login/kakaocode".equals(path)) {
			return true;
		} else if("/api/v1/login/renew".equals(path)) {
			return true;
		} else if("/api/v1/login/navercode".equals(path)) {
			return true;
		} else if("/api/v1/login/kakaologout".equals(path)) {
			return true;
		} else if("/swagger-ui/index.html".equals(path)) {
			return true;
		} else if("/swagger-ui.html".equals(path)) {
			return true;
		} else {
			return false;
		}
	}
}
