package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.Medida;

public class MedidaModel {
	private Long id;
	private String descricao;
	private String descricaoApresentacao;

	public MedidaModel(Medida medida) {
		this.id = medida.getId();
		this.descricao = medida.getDescricao();
		this.descricaoApresentacao = medida.getDescricaoApresentacao();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDescricaoApresentacao() {
		return descricaoApresentacao;
	}
}
