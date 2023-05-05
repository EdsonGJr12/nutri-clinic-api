package br.com.nutriclinic.api.form;

import java.math.BigDecimal;

import br.com.nutriclinic.domain.enuns.ProtocoloComposicaoCorporal;
import br.com.nutriclinic.domain.enuns.TipoComposicaoCorporal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AvaliacaoFisicaForm {
	
	@NotNull
	@Positive
	private BigDecimal altura;
	
	@NotNull
	@Positive
	private BigDecimal peso;
	
	@NotNull
	@Positive
	private BigDecimal bracoEsquerdoRelaxado;
	
	@NotNull
	@Positive
	private BigDecimal bracoDireitoRelaxado;
	
	@NotNull
	@Positive
	private BigDecimal bracoEsquerdoContraido;
	
	@NotNull
	@Positive
	private BigDecimal bracoDireitoContraido;
	
	@NotNull
	@Positive
	private BigDecimal antebracoDireito;
	
	@NotNull
	@Positive
	private BigDecimal antebracoEsquerdo;
	
	@NotNull
	@Positive
	private BigDecimal punhoDireito;
	
	@NotNull
	@Positive
	private BigDecimal punhoEsquerdo;
	
	@NotNull
	private TipoComposicaoCorporal tipoComposicaoCorporal;
	
//	@NotNull
	private ProtocoloComposicaoCorporal protocolo;
	
	@NotNull
	@Positive
	private BigDecimal biceps;
	
	@NotNull
	@Positive
	private BigDecimal abdominal;
	
	@NotNull
	@Positive
	private BigDecimal triceps;
	
	@NotNull
	@Positive
	private BigDecimal suprailica;
	
	@NotNull
	@Positive
	private BigDecimal axilarMedia;
	
	@NotNull
	@Positive
	private BigDecimal subscapular;
	
	@NotNull
	@Positive
	private BigDecimal torax;
	
	@NotNull
	@Positive
	private BigDecimal coxa;
	
	@NotNull
	@Positive
	private BigDecimal panturrilhaMedia;

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
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

	public TipoComposicaoCorporal getTipoComposicaoCorporal() {
		return tipoComposicaoCorporal;
	}

	public void setTipoComposicaoCorporal(TipoComposicaoCorporal tipoComposicaoCorporal) {
		this.tipoComposicaoCorporal = tipoComposicaoCorporal;
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
