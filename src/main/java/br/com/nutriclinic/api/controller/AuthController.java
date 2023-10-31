package br.com.nutriclinic.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.AuthForm;
import br.com.nutriclinic.api.model.AuthTokenModel;
import br.com.nutriclinic.config.TokenService;
import br.com.nutriclinic.domain.enuns.PerfilAcesso;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AtendimentoRepository;
import br.com.nutriclinic.domain.repository.UsuarioRepository;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.repository.entity.Usuario;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@PostMapping
	public AuthTokenModel autenticar(@RequestBody @Valid AuthForm authForm) {
		AuthTokenModel authTokenModel = new AuthTokenModel();
		
		Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getLogin(), authForm.getSenha()));
		Usuario usuarioAutenticado = (Usuario) authenticated.getPrincipal();
		String tokenAcesso = tokenService.gerarTokenAcesso(usuarioAutenticado.getId());
		Usuario usuario = usuarioRepository.findById(usuarioAutenticado.getId())
			.orElseThrow(() -> new NegocioException("Usuário não encontrado"));
		
		if (usuario.getPerfil().equals(PerfilAcesso.PACIENTE)) {
			authTokenModel.setIdPaciente(usuario.getId());
			
			Optional<Atendimento> atendimento = atendimentoRepository.findFirstByPacienteIdOrderByIdDesc(authTokenModel.getIdPaciente());
			if (atendimento.isPresent()) {
				authTokenModel.setIdPlanoAlimentar(atendimento.get().getPlanoAlimentar().getId());
			}
		}
		
		authTokenModel.setNomeUsuario(usuario.getNome());
		authTokenModel.setPerfilUsuario(usuario.getPerfil().getDescricao());
		authTokenModel.setToken(tokenAcesso);
		
		return authTokenModel;
	}
}
