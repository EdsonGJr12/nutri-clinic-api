package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;

public class PlanoAlimentarModel {
	private Long id;

	public PlanoAlimentarModel(PlanoAlimentar planoAlimentar) {
		this.id = planoAlimentar.getId();
	}

	public Long getId() {
		return id;
	}
}
