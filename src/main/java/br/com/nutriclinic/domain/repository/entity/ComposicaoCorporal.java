package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import br.com.nutriclinic.domain.enuns.ProtocoloComposicaoCorporal;
import br.com.nutriclinic.domain.enuns.TipoComposicaoCorporal;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ComposicaoCorporal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoComposicaoCorporal tipo;

	@Enumerated(EnumType.STRING)
	private ProtocoloComposicaoCorporal protocolo;

	private BigDecimal biceps;
	private BigDecimal abdominal;
	private BigDecimal triceps;
	private BigDecimal suprailiaca;
	private BigDecimal auxiliarMedia;
	private BigDecimal subscapular;
	private BigDecimal torax;
	private BigDecimal coxa;
	private BigDecimal panturrilhaMedia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoComposicaoCorporal getTipo() {
		return tipo;
	}

	public void setTipo(TipoComposicaoCorporal tipo) {
		this.tipo = tipo;
	}

	public ProtocoloComposicaoCorporal getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(ProtocoloComposicaoCorporal protocolo) {
		this.protocolo = protocolo;
	}

	public BigDecimal getBiceps() {
		return biceps;
	}

	public void setBiceps(BigDecimal biceps) {
		this.biceps = biceps;
	}

	public BigDecimal getAbdominal() {
		return abdominal;
	}

	public void setAbdominal(BigDecimal abdominal) {
		this.abdominal = abdominal;
	}

	public BigDecimal getTriceps() {
		return triceps;
	}

	public void setTriceps(BigDecimal triceps) {
		this.triceps = triceps;
	}

	public BigDecimal getSuprailiaca() {
		return suprailiaca;
	}

	public void setSuprailiaca(BigDecimal suprailiaca) {
		this.suprailiaca = suprailiaca;
	}

	public BigDecimal getAuxiliarMedia() {
		return auxiliarMedia;
	}

	public void setAuxiliarMedia(BigDecimal auxiliarMedia) {
		this.auxiliarMedia = auxiliarMedia;
	}

	public BigDecimal getSubscapular() {
		return subscapular;
	}

	public void setSubscapular(BigDecimal subscapular) {
		this.subscapular = subscapular;
	}

	public BigDecimal getTorax() {
		return torax;
	}

	public void setTorax(BigDecimal torax) {
		this.torax = torax;
	}

	public BigDecimal getCoxa() {
		return coxa;
	}

	public void setCoxa(BigDecimal coxa) {
		this.coxa = coxa;
	}

	public BigDecimal getPanturrilhaMedia() {
		return panturrilhaMedia;
	}

	public void setPanturrilhaMedia(BigDecimal panturrilhaMedia) {
		this.panturrilhaMedia = panturrilhaMedia;
	}

}
