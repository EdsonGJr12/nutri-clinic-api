package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.Alimento;

public class AlimentoModel {
	private Long id;
	private String descricao;

	public AlimentoModel(Alimento alimento) {
		this.id = alimento.getId();
		this.descricao = alimento.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
