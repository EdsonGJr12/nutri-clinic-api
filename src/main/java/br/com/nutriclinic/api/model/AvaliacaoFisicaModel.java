package br.com.nutriclinic.api.model;

import java.util.List;

import br.com.nutriclinic.domain.repository.entity.AvaliacaoFisica;

public class AvaliacaoFisicaModel {

	private Long id;
	private List<ResultadoAvaliacaoFisicaModel> resultados;

	public AvaliacaoFisicaModel(AvaliacaoFisica avaliacaoFisica, List<ResultadoAvaliacaoFisicaModel> resultados) {
		this.id = avaliacaoFisica.getId();
		this.resultados = resultados;
	}

	public Long getId() {
		return id;
	}

	public List<ResultadoAvaliacaoFisicaModel> getResultados() {
		return resultados;
	}

}
