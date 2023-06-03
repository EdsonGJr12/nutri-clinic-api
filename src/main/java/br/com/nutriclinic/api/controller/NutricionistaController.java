package br.com.nutriclinic.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.PacienteHistoricoModel;
import br.com.nutriclinic.api.model.PacienteModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.NutricionistaRepository;
import br.com.nutriclinic.domain.repository.PacienteHistoricoRepository;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.repository.entity.Nutricionista;
import br.com.nutriclinic.domain.repository.entity.Paciente;
import br.com.nutriclinic.domain.repository.entity.PacienteHistorico;

@RestController
@RequestMapping("/nutricionista")
@CrossOrigin
public class NutricionistaController {
	
	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	@Autowired
	private PacienteHistoricoRepository pacienteHistoricoRepository;
	
	@GetMapping("/pacientes")
	public List<PacienteModel> pesquisarPacientes(Authentication authentication) {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
		
		Nutricionista nutricionista = nutricionistaRepository.findById(usuarioAutenticado.getId())
			.orElseThrow(() -> new NegocioException("Nutricionista não encontrado"));
		
		List<Paciente> pacientes = nutricionista.getAtendimentos().stream()
			.map(Atendimento::getPaciente)
			.toList();
		
		return pacientes.stream()
				.map(PacienteModel::new)
				.toList();
	}
	
	@GetMapping("/pacientes/{idPaciente}/historico")
	public List<PacienteHistoricoModel> pesquisarHistoricoDoPaciente(@PathVariable Long idPaciente) {
		List<PacienteHistorico> historico = pacienteHistoricoRepository.findByPacienteId(idPaciente);
		
		return historico.stream()
				.map(PacienteHistoricoModel::new)
				.toList();
	}
}
