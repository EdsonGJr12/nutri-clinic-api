package br.com.nutriclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nutriclinic.repository.UsuarioRepository;
import br.com.nutriclinic.repository.entity.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOp = usuarioRepository.findByLogin(login);
		if (usuarioOp.isPresent()) {
			return usuarioOp.get();
		}
		throw new UsernameNotFoundException("Usuário ou senha inválidos");
	}

}
