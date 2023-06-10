package br.com.nutriclinic.api.model;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.nutriclinic.domain.repository.entity.Refeicao;

public class RefeicaoModel {
	
	private Long id;
	private String descricao;
	private LocalTime horario;
	private List<RefeicaoAlimentoModel> alimentos;

	public RefeicaoModel(Refeicao refeicao) {
		this.id = refeicao.getId();
		this.descricao = refeicao.getDescricao();
		this.horario = refeicao.getHorario();
		this.alimentos = refeicao.getAlimentos().stream()
				.map(RefeicaoAlimentoModel::new)
				.collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public List<RefeicaoAlimentoModel> getAlimentos() {
		return alimentos;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public LocalTime getHorario() {
		return horario;
	}

}
