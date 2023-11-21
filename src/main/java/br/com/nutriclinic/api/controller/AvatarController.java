package br.com.nutriclinic.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.UsuarioRepository;
import br.com.nutriclinic.domain.repository.entity.Usuario;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/{avatar}")
	public ResponseEntity<InputStreamResource> buscarAvatar(@PathVariable String avatar) throws IOException {
		
		Usuario usuario = usuarioRepository.findByAvatar(avatar)
			.orElseThrow(() -> new NegocioException("Usuário não encontrado"));
		
		Path diretorioLocal = Path.of("/home/edson/nutri-clinic/users");
		
		Path diretorioAvatar = diretorioLocal.resolve(usuario.getAvatar());
		
		InputStream inputStream = Files.newInputStream(diretorioAvatar);
		
		MediaType mediaType = MediaType.parseMediaType(usuario.getAvatarContentType());
		
		return ResponseEntity.ok()
				.contentType(mediaType)
				.body(new InputStreamResource(inputStream));
		
	}
	
}
