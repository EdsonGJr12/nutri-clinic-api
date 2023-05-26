package br.com.nutriclinic.api.model;

import java.math.BigDecimal;

public class ImcModel {
	private BigDecimal valor;
	private String recomendacao;
	private String situacao;

	public ImcModel(BigDecimal valor, String recomendacao, String situacao) {
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
