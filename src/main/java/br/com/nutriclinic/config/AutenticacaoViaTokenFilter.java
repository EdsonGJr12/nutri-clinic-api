package br.com.nutriclinic.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.nutriclinic.exception.NegocioException;
import br.com.nutriclinic.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	public AutenticacaoViaTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromHeader(request);
		boolean isTokenValido = tokenService.isTokenAcessoValido(token);
		if (isTokenValido) {
			autenticarUsuario(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) throws RuntimeException {
		try {
			UsuarioAutenticado usuarioAutenticado = tokenService.getUsuarioAutenticado(token);
			Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}
}
