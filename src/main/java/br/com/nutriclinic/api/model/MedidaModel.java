package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.Medida;

public class MedidaModel {
	private Long id;
	private String descrica;

	public MedidaModel(Medida medida) {
		this.id = medida.getId();
		this.descrica = medida.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getDescrica() {
		return descrica;
	}
}
