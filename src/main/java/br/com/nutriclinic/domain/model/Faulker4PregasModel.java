package br.com.nutriclinic.domain.model;

import java.math.BigDecimal;

public class Faulker4PregasModel {
	private BigDecimal valor;
	private String recomendacao;
	private String situacao;

	public Faulker4PregasModel(BigDecimal valor, String recomendacao, String situacao) {
		this.valor = valor;
		this.recomendacao = recomendacao;
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public String getSituacao() {
		return situacao;
	}
}
