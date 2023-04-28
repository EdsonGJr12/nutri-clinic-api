package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import br.com.nutriclinic.domain.enums.ProtocoloComposicaoCorporal;
import br.com.nutriclinic.domain.enuns.TipoComposicaoCorporal;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class ComposicaoCorporal {

	@Id
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id_avaliacao_fisica")
	private AvaliacaoFisica avaliacaoFisica;

	@Enumerated(EnumType.STRING)
	private TipoComposicaoCorporal tipo;

	@Enumerated(EnumType.STRING)
	private ProtocoloComposicaoCorporal protocolo;

	private BigDecimal biceps;
	private BigDecimal abdominal;
	private BigDecimal triceps;
	private BigDecimal suprailica;
	private BigDecimal axilarMedia;
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

	public AvaliacaoFisica getAvaliacaoFisica() {
		return avaliacaoFisica;
	}

	public void setAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
		this.avaliacaoFisica = avaliacaoFisica;
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

	public BigDecimal getSuprailica() {
		return suprailica;
	}

	public void setSuprailica(BigDecimal suprailica) {
		this.suprailica = suprailica;
	}

	public BigDecimal getAxilarMedia() {
		return axilarMedia;
	}

	public void setAxilarMedia(BigDecimal axilarMedia) {
		this.axilarMedia = axilarMedia;
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
