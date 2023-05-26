package br.com.nutriclinic.api.model;

import java.math.BigDecimal;

public class ResultadoAvaliacaoFisicaModel {
	private String parametro;
	private BigDecimal valorAtual;
	private String recomendacao;
	private String situacao;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
