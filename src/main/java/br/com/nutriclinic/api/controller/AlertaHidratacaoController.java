package br.com.nutriclinic.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.AlertaHidratacaoForm;
import br.com.nutriclinic.api.model.AlertaHidratacaoModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AlertaHidratacaoRepository;
import br.com.nutriclinic.domain.repository.UsuarioRepository;
import br.com.nutriclinic.domain.repository.entity.AlertaHidratacao;
import br.com.nutriclinic.domain.repository.entity.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alertas-hidratacao")
public class AlertaHidratacaoController {
	
	@Autowired
	private AlertaHidratacaoRepository alertaHidratacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<AlertaHidratacaoModel> pesquisarAlertas(@RequestParam Long idUsuario) {
		List<AlertaHidratacao> horarios = alertaHidratacaoRepository.findByUsuarioId(idUsuario);
		return horarios.stream()
				.map(AlertaHidratacaoModel::new)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	@Transactional
	public void cadastrarAlertas(@RequestBody @Valid AlertaHidratacaoForm form) {
		Usuario usuario = usuarioRepository.findById(form.getIdUsuario())
				.orElseThrow(() -> new NegocioException("Usuário não encontrado"));

		List<AlertaHidratacao> horariosParaDeletar = alertaHidratacaoRepository.findByUsuarioId(form.getIdUsuario());
		alertaHidratacaoRepository.deleteAll(horariosParaDeletar);
		
		List<AlertaHidratacao> horarios = form.getHorarios().stream()
			.map(item -> {
				AlertaHidratacao alertaHidratacao = new AlertaHidratacao();
				alertaHidratacao.setHorario(item);
				alertaHidratacao.setUsuario(usuario);
				return alertaHidratacao;
			}).toList();
		alertaHidratacaoRepository.saveAll(horarios);
	}
}	
