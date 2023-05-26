package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Imc {

	@Id
	private Long id;

	private String descricao;

	private BigDecimal faixaInicio;

	private BigDecimal faixaFim;

	private String classificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getFaixaInicio() {
		return faixaInicio;
	}

	public void setFaixaInicio(BigDecimal faixaInicio) {
		this.faixaInicio = faixaInicio;
	}

	public BigDecimal getFaixaFim() {
		return faixaFim;
	}

	public void setFaixaFim(BigDecimal faixaFim) {
		this.faixaFim = faixaFim;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

}
