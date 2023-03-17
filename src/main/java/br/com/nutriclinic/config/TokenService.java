package br.com.nutriclinic.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	@Value("${jwt.token.secret}")
	private String jwtSecret;

	public String gerarTokenAcesso(Long idUsuario) {
		Date agora = new Date();

		SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

		return Jwts.builder()
				.setIssuer("API do Sistema")
				.setSubject(idUsuario.toString())
				.setIssuedAt(agora)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

	}

	public boolean isTokenAcessoValido(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public UsuarioAutenticado getUsuarioAutenticado(String token) {
		SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
		return new UsuarioAutenticado(Long.valueOf(subject));
	}

}
