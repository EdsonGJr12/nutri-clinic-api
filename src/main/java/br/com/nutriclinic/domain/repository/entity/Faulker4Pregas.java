package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import br.com.nutriclinic.domain.enuns.Sexo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faulkner_4_pregas")
public class Faulker4Pregas {

	@Id
	private Integer id;

	private Integer idadeInicio;
	private Integer idadeFim;
	private BigDecimal faixaInicio;
	private BigDecimal faixaFim;
	private String classificacao;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdadeInicio() {
		return idadeInicio;
	}

	public void setIdadeInicio(Integer idadeInicio) {
		this.idadeInicio = idadeInicio;
	}

	public Integer getIdadeFim() {
		return idadeFim;
	}

	public void setIdadeFim(Integer idadeFim) {
		this.idadeFim = idadeFim;
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
