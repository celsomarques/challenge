package br.com.atech.challenge.security;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		
		ObjectNode json = new ObjectMapper().createObjectNode();
		json.put("token", token);

		response.setContentType(APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
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