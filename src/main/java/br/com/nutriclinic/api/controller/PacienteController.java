package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.PacienteModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.repository.PacienteRepository;
import br.com.nutriclinic.domain.repository.entity.Paciente;

@RestController
@RequestMapping("/nutricionista")
@CrossOrigin
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@GetMapping("/pacientes")
	public List<PacienteModel> pesquisarPacientes(Authentication authentication) {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
		List<Paciente> pacientes = pacienteRepository.findByNutricionistaId(usuarioAutenticado.getId());
		return pacientes.stream()
				.map(PacienteModel::new)
				.toList();
	}
}
