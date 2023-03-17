package br.com.nutriclinic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.AuthForm;
import br.com.nutriclinic.api.model.AuthTokenModel;
import br.com.nutriclinic.config.TokenService;
import br.com.nutriclinic.domain.repository.entity.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public AuthTokenModel autenticar(@RequestBody @Valid AuthForm authForm) {
		Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getLogin(), authForm.getSenha()));
		Usuario usuarioAutenticado = (Usuario)authenticated.getPrincipal();
		String tokenAcesso = tokenService.gerarTokenAcesso(usuarioAutenticado.getId());
		return new AuthTokenModel(tokenAcesso);
	}
}
