package br.com.nutriclinic.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.PlanoAlimentarInformacoesBasicasForm;
import br.com.nutriclinic.api.form.RefeicaoAlimentoForm;
import br.com.nutriclinic.api.form.RefeicaoForm;
import br.com.nutriclinic.api.model.DiaSemanaRefeicaoModel;
import br.com.nutriclinic.api.model.RefeicaoModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.PlanoAlimentarRepository;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;
import br.com.nutriclinic.domain.service.PlanoAlimentarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/planos-alimentares")
public class PlanoAlimentarController {
	
	@Autowired
	private PlanoAlimentarService planoAlimentarService;
	
	@Autowired
	private PlanoAlimentarRepository planoAlimentarRepository;
	
	@PutMapping("/{idPlanoAlimentar}/informacoes-basicas")
	public void alterarInformacoesPlanoAlimentar(@PathVariable Long idPlanoAlimentar, @RequestBody @Valid PlanoAlimentarInformacoesBasicasForm planoAlimentar) {
		planoAlimentarService.alterarInformacoesBasicas(idPlanoAlimentar, planoAlimentar);
	}
	
	@GetMapping("/{idPlanoAlimentar}")
	public List<DiaSemanaRefeicaoModel> pesquisarPlano(@PathVariable Long idPlanoAlimentar) {
		return planoAlimentarService.pesquisarPlano(idPlanoAlimentar);
	}
	
	@GetMapping("/{idPlanoAlimentar}/{diaSemana}/refeicoes")
	public List<RefeicaoModel> pesquisarRefeicoesDoPlano(@PathVariable Long idPlanoAlimentar, @PathVariable Integer diaSemana) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		return planoAlimentar.getDias().stream()
				.filter(dia -> dia.getDiaSemana().equals(diaSemana))
				.findFirst()
				.get()
				.getRefeicoes().stream()
				.sorted((refeicao1, refeicao2) -> refeicao1.getHorario().compareTo(refeicao2.getHorario()))
				.map(RefeicaoModel::new)
				.collect(Collectors.toList());
	}
	
	@PostMapping("/{idPlanoAlimentar}/refeicoes")
	public void adicionarRefeicaoNoPlano(@PathVariable Long idPlanoAlimentar, @RequestBody @Valid RefeicaoForm refeicaoForm) {
		planoAlimentarService.adicionarRefeicaoNoPlano(idPlanoAlimentar, refeicaoForm);
	}
	
	@DeleteMapping("/{idPlanoAlimentar}/refeicoes/{idRefeicao}")
	public void removerRefeicaoDoPlano(@PathVariable Long idPlanoAlimentar, @PathVariable Long idRefeicao) {
		planoAlimentarService.removerRefeicaoDoPlano(idPlanoAlimentar, idRefeicao);
	}
	
	@PostMapping("/{idPlanoAlimentar}/refeicoes/{idRefeicao}/alimentos")
	public void adicionarAlimentoNaRefeicao(@PathVariable Long idPlanoAlimentar, @PathVariable Long idRefeicao, 
			@RequestBody @Valid RefeicaoAlimentoForm alimentoForm) {
		planoAlimentarService.adicionarAlimentoNaRefeicao(idPlanoAlimentar, idRefeicao, alimentoForm);
	}
	
	@DeleteMapping("/{idPlanoAlimentar}/refeicoes/{idRefeicao}/alimentos/{idAlimento}")
	public void removerAlimentoNaRefeicao(@PathVariable Long idPlanoAlimentar, @PathVariable Long idRefeicao, 
			@PathVariable Long idAlimento) {
		planoAlimentarService.removerAlimentoDaRefeicao(idPlanoAlimentar, idRefeicao, idAlimento);
	}
	
	@PutMapping("/{idPlanoAlimentar}/refeicoes/{idRefeicao}/alimentos/{idAlimento}")
	public void editarAlimentoDaRefeicao(@PathVariable Long idPlanoAlimentar, @PathVariable Long idRefeicao, 
			@PathVariable Long idAlimento, @RequestBody @Valid RefeicaoAlimentoForm refeicaoAlimentoForm) {
		planoAlimentarService.editarAlimentoDaRefeicao(idPlanoAlimentar, idRefeicao, idAlimento, refeicaoAlimentoForm);
	}
}
