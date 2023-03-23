package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.CadastrarUsuarioForm;
import br.com.nutriclinic.api.model.UsuarioModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.UsuarioRepository;
import br.com.nutriclinic.domain.repository.entity.Usuario;
import jakarta.validation.Valid;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<UsuarioModel> pesquisarUsuarios() {
		return usuarioRepository.findAll().stream()
				.map(UsuarioModel::new)
				.toList();
	}
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid CadastrarUsuarioForm form) {
		Usuario usuario = new Usuario();
		usuario.setLogin(form.getCpf());
		usuario.setNome(form.getNome());
		
		String senhaCriptografada = passwordEncoder.encode(form.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		usuarioRepository.save(usuario);
	}
	
	@PutMapping("/{id}")
	public void editar(@PathVariable Long id, @RequestBody @Valid CadastrarUsuarioForm form) {
		
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new NegocioException("Usuário não encontrado"));
		
		usuario.setLogin(form.getCpf());
		usuario.setNome(form.getNome());
		
		String senhaCriptografada = passwordEncoder.encode(form.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		usuarioRepository.save(usuario);
	}
}
