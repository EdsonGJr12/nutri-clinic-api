package br.com.nutriclinic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.AtendimentoPacienteForm;
import br.com.nutriclinic.api.form.AvaliacaoFisicaForm;
import br.com.nutriclinic.api.model.AtendimentoPacienteModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.service.AtendimentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping("/paciente")
	public ResponseEntity<AtendimentoPacienteModel> iniciarAtendimento(@RequestBody @Valid AtendimentoPacienteForm atendimentoPacienteForm, Authentication authentication) {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
		Atendimento novoAtendimento = atendimentoService.iniciarAtendimento(atendimentoPacienteForm, usuarioAutenticado);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new AtendimentoPacienteModel(novoAtendimento));
	}
	
	@PostMapping("/{idAtendimento}/avaliacao-fisica")
	public void registrarAvaliacaoFisica(@PathVariable Long idAtendimento, @RequestBody @Valid AvaliacaoFisicaForm avaliacaoFisicaForm) {
		atendimentoService.registrarAvaliacaoFisica(idAtendimento, avaliacaoFisicaForm);
	}
	
	@PostMapping("/plano-alimentar")
	public void registrarPlanoAlimentar() {
		
	}
	
}
