package br.com.atech.challenge.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class TokenAuthenticationService {

	static final long ONE_HOUR = 60 * 60 * 1000;
	static final String SECRET = "Challenge-Atech";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse response, String username) throws IOException {
		String token = Jwts
				.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + ONE_HOUR))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(String.format("{ \"token\": \"%s\" }", token));
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token == null) return null;
		
		String user = Jwts
				.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();

		if (user == null) return null;
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}
}