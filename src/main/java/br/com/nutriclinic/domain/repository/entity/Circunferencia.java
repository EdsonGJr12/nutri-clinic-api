package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Circunferencia {

	@Id
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id_avaliacao_fisica")
	private AvaliacaoFisica avaliacaoFisica;

	private BigDecimal bracoEsquerdoRelaxado;
	private BigDecimal bracoDireitoRelaxado;
	private BigDecimal bracoEsquerdoContraido;
	private BigDecimal bracoDireitoContraido;
	private BigDecimal antebracoDireito;
	private BigDecimal antebracoEsquerdo;
	private BigDecimal punhoDireito;
	private BigDecimal punhoEsquerdo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBracoEsquerdoRelaxado() {
		return bracoEsquerdoRelaxado;
	}

	public void setBracoEsquerdoRelaxado(BigDecimal bracoEsquerdoRelaxado) {
		this.bracoEsquerdoRelaxado = bracoEsquerdoRelaxado;
	}

	public BigDecimal getBracoDireitoRelaxado() {
		return bracoDireitoRelaxado;
	}

	public void setBracoDireitoRelaxado(BigDecimal bracoDireitoRelaxado) {
		this.bracoDireitoRelaxado = bracoDireitoRelaxado;
	}

	public BigDecimal getBracoEsquerdoContraido() {
		return bracoEsquerdoContraido;
	}

	public void setBracoEsquerdoContraido(BigDecimal bracoEsquerdoContraido) {
		this.bracoEsquerdoContraido = bracoEsquerdoContraido;
	}

	public BigDecimal getBracoDireitoContraido() {
		return bracoDireitoContraido;
	}

	public void setBracoDireitoContraido(BigDecimal bracoDireitoContraido) {
		this.bracoDireitoContraido = bracoDireitoContraido;
	}

	public BigDecimal getAntebracoDireito() {
		return antebracoDireito;
	}

	public void setAntebracoDireito(BigDecimal antebracoDireito) {
		this.antebracoDireito = antebracoDireito;
	}

	public BigDecimal getAntebracoEsquerdo() {
		return antebracoEsquerdo;
	}

	public void setAntebracoEsquerdo(BigDecimal antebracoEsquerdo) {
		this.antebracoEsquerdo = antebracoEsquerdo;
	}

	public BigDecimal getPunhoDireito() {
		return punhoDireito;
	}

	public void setPunhoDireito(BigDecimal punhoDireito) {
		this.punhoDireito = punhoDireito;
	}

	public BigDecimal getPunhoEsquerdo() {
		return punhoEsquerdo;
	}

	public void setPunhoEsquerdo(BigDecimal punhoEsquerdo) {
		this.punhoEsquerdo = punhoEsquerdo;
	}

}
