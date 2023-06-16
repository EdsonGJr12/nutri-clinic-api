package br.com.nutriclinic.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.AtendimentoPacienteForm;
import br.com.nutriclinic.api.form.AvaliacaoFisicaForm;
import br.com.nutriclinic.api.form.PlanoAlimentarForm;
import br.com.nutriclinic.api.model.AtendimentoPacienteModel;
import br.com.nutriclinic.api.model.AvaliacaoFisicaModel;
import br.com.nutriclinic.api.model.PacienteModel;
import br.com.nutriclinic.api.model.PlanoAlimentarModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AtendimentoRepository;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.service.AtendimentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@PostMapping("/paciente")
	@ResponseStatus(HttpStatus.CREATED)
	public AtendimentoPacienteModel iniciarAtendimento(@RequestBody @Valid AtendimentoPacienteForm atendimentoPacienteForm, Authentication authentication) {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
		Atendimento novoAtendimento = atendimentoService.iniciarAtendimento(atendimentoPacienteForm, usuarioAutenticado);
		return new AtendimentoPacienteModel(novoAtendimento);
	}
	
	@GetMapping("/{idAtendimento}/paciente")
	public PacienteModel buscarPaciente(@PathVariable Long idAtendimento) {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById(idAtendimento);
		if (atendimentoOp.isPresent()) {
			return new PacienteModel(atendimentoOp.get().getPaciente());
		} else {
			throw new NegocioException("Atendimento não encontrado");
		}
	}
	
	@GetMapping("/{idAtendimento}/avaliacao-fisica")
	public AvaliacaoFisicaModel buscarAvaliacaoFisica(@PathVariable Long idAtendimento) {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById(idAtendimento);
		if (atendimentoOp.isPresent() && atendimentoOp.get().getAvaliacaoFisica() != null) {
			return atendimentoService.montarResultadoAvalicaoFisica(atendimentoOp.get(), atendimentoOp.get().getAvaliacaoFisica());
		} else {
			throw new NegocioException("Atendimento ou avaliação física não encontrada");
		}
	}
	
	@PostMapping("/{idAtendimento}/avaliacao-fisica")
	@ResponseStatus(HttpStatus.CREATED)
	public AvaliacaoFisicaModel registrarAvaliacaoFisica(@PathVariable Long idAtendimento, @RequestBody @Valid AvaliacaoFisicaForm avaliacaoFisicaForm) {
		return atendimentoService.registrarAvaliacaoFisica(idAtendimento, avaliacaoFisicaForm);
	}
	
	@PostMapping("/{idAtendimento}/plano-alimentar")
	@ResponseStatus(HttpStatus.CREATED)
	public PlanoAlimentarModel registrarPlanoAlimentar(@PathVariable Long idAtendimento, 
			@RequestBody @Valid PlanoAlimentarForm planoAlimentarForm, Authentication authentication) {
		UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
		return atendimentoService.registrarPlanoAlimentar(idAtendimento, planoAlimentarForm, usuarioAutenticado);
	}
	
	@GetMapping("/{idAtendimento}/plano-alimentar")
	@ResponseStatus(HttpStatus.CREATED)
	public PlanoAlimentarModel buscarPlanoAlimentar(@PathVariable Long idAtendimento, Authentication authentication) {
		Optional<Atendimento> atendimentoOp = atendimentoRepository.findById(idAtendimento);
		if (atendimentoOp.isPresent()) {
			return new PlanoAlimentarModel(atendimentoOp.get().getPlanoAlimentar());
		} else {
			throw new NegocioException("Atendimento não encontrado");
		}
	}
	
}
