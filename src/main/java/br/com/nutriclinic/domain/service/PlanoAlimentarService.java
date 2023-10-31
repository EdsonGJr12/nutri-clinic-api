package br.com.nutriclinic.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nutriclinic.api.form.PlanoAlimentarInformacoesBasicasForm;
import br.com.nutriclinic.api.form.RefeicaoAlimentoForm;
import br.com.nutriclinic.api.form.RefeicaoForm;
import br.com.nutriclinic.api.model.DiaSemanaRefeicaoModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.PlanoAlimentarRepository;
import br.com.nutriclinic.domain.repository.entity.Alimento;
import br.com.nutriclinic.domain.repository.entity.Medida;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentarDiaSemana;
import br.com.nutriclinic.domain.repository.entity.Refeicao;
import br.com.nutriclinic.domain.repository.entity.RefeicaoAlimento;

@Service
public class PlanoAlimentarService {

	@Autowired
	private PlanoAlimentarRepository planoAlimentarRepository;
	
	public List<DiaSemanaRefeicaoModel> pesquisarPlano(Long idPlanoAlimentar) {
		
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		
		return planoAlimentar.getDias().stream()
				.map(DiaSemanaRefeicaoModel::new)
				.collect(Collectors.toList()); 
	}

	@Transactional
	public void alterarInformacoesBasicas(Long idPlanoAlimentar,
			PlanoAlimentarInformacoesBasicasForm planoAlimentarForm) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));

		planoAlimentar.setDescricao(planoAlimentarForm.getDescricao());
		planoAlimentar.setDescricao(planoAlimentarForm.getDescricao());
		planoAlimentar.setSegunda(planoAlimentarForm.getSegunda());
		planoAlimentar.setTerca(planoAlimentarForm.getTerca());
		planoAlimentar.setQuarta(planoAlimentarForm.getQuarta());
		planoAlimentar.setQuinta(planoAlimentarForm.getQuinta());
		planoAlimentar.setSexta(planoAlimentarForm.getSexta());
		planoAlimentar.setSabado(planoAlimentarForm.getSabado());
		planoAlimentar.setDomingo(planoAlimentarForm.getDomingo());
	}

	@Transactional
	public void adicionarRefeicaoNoPlano(Long idPlanoAlimentar, RefeicaoForm refeicaoForm) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		
		Refeicao refeicao = getRefeicao(refeicaoForm);
		
		planoAlimentar.adicionarRefeicao(refeicao);

	}
	
	@Transactional
	public void removerRefeicaoDoPlano(Long idPlanoAlimentar, Long idRefeicao) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		planoAlimentar.removerRefeicao(idRefeicao);
	}
	
	@Transactional
	public void adicionarAlimentoNaRefeicao(Long idPlanoAlimentar, Long idRefeicao, RefeicaoAlimentoForm alimentoForm) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		
		RefeicaoAlimento refeicaoAlimento = getAlimento(alimentoForm);
		
		planoAlimentar.getDias().forEach(dia -> {
			dia.getRefeicoes().forEach(refeicao -> {
				if (refeicao.getId().equals(idRefeicao)) {
					refeicao.adicionarRefeicao(refeicaoAlimento);
				}
			});
		});
	}
	
	@Transactional
	public void removerAlimentoDaRefeicao(Long idPlanoAlimentar, Long idRefeicao, Long idAlimento) {
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		
		planoAlimentar.getDias().forEach(dia -> {
			dia.getRefeicoes().forEach(refeicao -> {
				if (refeicao.getId().equals(idRefeicao)) {
					refeicao.removerAlimento(idAlimento);			
				}
			});
		});
	}
	
	@Transactional
	public void editarAlimentoDaRefeicao(Long idPlanoAlimentar, Long idRefeicao, Long idAlimento, 
			RefeicaoAlimentoForm refeicaoAlimentoForm) {
		
		PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(idPlanoAlimentar)
				.orElseThrow(() -> new NegocioException("Plano alimentar não encontrado"));
		
		List<PlanoAlimentarDiaSemana> dias = planoAlimentar.getDias();
		
		dias.forEach(dia -> {
			dia.getRefeicoes().forEach(refeicao -> {
				if (refeicao.getId().equals(idRefeicao)) {
					RefeicaoAlimento refeicaoAlimento = refeicao.getAlimentos().stream()
							.filter(alimento -> alimento.getId().equals(idAlimento))
							.findFirst()
							.get();
					
					refeicaoAlimento.setAlimento(new Alimento(refeicaoAlimentoForm.getIdAlimento()));
					refeicaoAlimento.setMedida(new Medida(refeicaoAlimentoForm.getIdMedida()));
					refeicaoAlimento.setQuantidade(refeicaoAlimentoForm.getQuantidade());
				}
			});
		});
		
	}
	
	private Refeicao getRefeicao(RefeicaoForm refeicaoForm) {
		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao(refeicaoForm.getDescricao());
		refeicao.setHorario(refeicaoForm.getHorario());
		
		List<RefeicaoAlimento> alimentos = getAlimentos(refeicaoForm);
		
		refeicao.setAlimentos(alimentos);
		
		return refeicao;
	}

	private List<RefeicaoAlimento> getAlimentos(RefeicaoForm refeicaoForm) {
		List<RefeicaoAlimento> alimentos = refeicaoForm.getAlimentos().stream()
			.map(this::getAlimento)
			.collect(Collectors.toList());
		return alimentos;
	}

	private RefeicaoAlimento getAlimento(RefeicaoAlimentoForm alimentoForm) {
		RefeicaoAlimento alimento = new RefeicaoAlimento();
		alimento.setAlimento(new Alimento(alimentoForm.getIdAlimento()));
		alimento.setQuantidade(alimentoForm.getQuantidade());
		alimento.setMedida(new Medida(alimentoForm.getIdMedida()));
		
		return alimento;
	}
}
