package br.com.nutriclinic.api.model;

import java.math.BigDecimal;
import java.util.List;

import br.com.nutriclinic.domain.enuns.ProtocoloComposicaoCorporal;
import br.com.nutriclinic.domain.enuns.TipoComposicaoCorporal;
import br.com.nutriclinic.domain.repository.entity.AvaliacaoFisica;
import br.com.nutriclinic.domain.repository.entity.Circunferencia;
import br.com.nutriclinic.domain.repository.entity.ComposicaoCorporal;

public class AvaliacaoFisicaModel {

	private Long id;
	private BigDecimal altura;
	private BigDecimal peso;
	private BigDecimal bracoEsquerdoRelaxado;
	private BigDecimal bracoDireitoRelaxado;
	private BigDecimal bracoEsquerdoContraido;
	private BigDecimal bracoDireitoContraido;
	private BigDecimal antebracoDireito;
	private BigDecimal antebracoEsquerdo;
	private BigDecimal punhoDireito;
	private BigDecimal punhoEsquerdo;
	private TipoComposicaoCorporal tipoComposicaoCorporal;
	private ProtocoloComposicaoCorporal protoloco;
	private BigDecimal biceps;
	private BigDecimal abdominal;
	private BigDecimal triceps;
	private BigDecimal suprailiaca;
	private BigDecimal axilarMedia;
	private BigDecimal subscapular;
	private BigDecimal torax;
	private BigDecimal coxa;
	private BigDecimal panturrilhaMedial;

	private List<ResultadoAvaliacaoFisicaModel> resultados;

	public AvaliacaoFisicaModel(AvaliacaoFisica avaliacaoFisica, List<ResultadoAvaliacaoFisicaModel> resultados) {
		this.id = avaliacaoFisica.getId();
		this.altura = avaliacaoFisica.getAltura();
		this.peso = avaliacaoFisica.getPeso();

		Circunferencia circunferencia = avaliacaoFisica.getCircunferencia();
		this.bracoEsquerdoRelaxado = circunferencia.getBracoEsquerdoRelaxado();
		this.bracoDireitoRelaxado = circunferencia.getBracoDireitoRelaxado();
		this.bracoEsquerdoContraido = circunferencia.getBracoEsquerdoContraido();
		this.bracoDireitoContraido = circunferencia.getBracoDireitoContraido();
		this.antebracoEsquerdo = circunferencia.getAntebracoEsquerdo();
		this.antebracoDireito = circunferencia.getAntebracoDireito();
		this.punhoDireito = circunferencia.getPunhoDireito();
		this.punhoEsquerdo = circunferencia.getPunhoEsquerdo();

		ComposicaoCorporal composicaoCorporal = avaliacaoFisica.getComposicaoCorporal();
		this.tipoComposicaoCorporal = composicaoCorporal.getTipo();
		this.protoloco = composicaoCorporal.getProtocolo();
		this.biceps = composicaoCorporal.getBiceps();
		this.abdominal = composicaoCorporal.getAbdominal();
		this.triceps = composicaoCorporal.getTriceps();
		this.suprailiaca = composicaoCorporal.getSuprailiaca();
		this.axilarMedia = composicaoCorporal.getAxilarMedia();
		this.subscapular = composicaoCorporal.getSubscapular();
		this.torax = composicaoCorporal.getTorax();
		this.coxa = composicaoCorporal.getCoxa();
		this.panturrilhaMedial = composicaoCorporal.getPanturrilhaMedial();

		this.resultados = resultados;
	}

	public Long getId() {
		return id;
	}

	public List<ResultadoAvaliacaoFisicaModel> getResultados() {
		return resultados;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public BigDecimal getBracoEsquerdoRelaxado() {
		return bracoEsquerdoRelaxado;
	}

	public BigDecimal getBracoDireitoRelaxado() {
		return bracoDireitoRelaxado;
	}

	public BigDecimal getBracoEsquerdoContraido() {
		return bracoEsquerdoContraido;
	}

	public BigDecimal getBracoDireitoContraido() {
		return bracoDireitoContraido;
	}

	public BigDecimal getAntebracoDireito() {
		return antebracoDireito;
	}

	public BigDecimal getAntebracoEsquerdo() {
		return antebracoEsquerdo;
	}

	public BigDecimal getPunhoDireito() {
		return punhoDireito;
	}

	public BigDecimal getPunhoEsquerdo() {
		return punhoEsquerdo;
	}

	public TipoComposicaoCorporal getTipoComposicaoCorporal() {
		return tipoComposicaoCorporal;
	}

	public ProtocoloComposicaoCorporal getProtoloco() {
		return protoloco;
	}

	public BigDecimal getBiceps() {
		return biceps;
	}

	public BigDecimal getAbdominal() {
		return abdominal;
	}

	public BigDecimal getTriceps() {
		return triceps;
	}

	public BigDecimal getSuprailiaca() {
		return suprailiaca;
	}

	public BigDecimal getSubscapular() {
		return subscapular;
	}

	public BigDecimal getTorax() {
		return torax;
	}

	public BigDecimal getCoxa() {
		return coxa;
	}

	public BigDecimal getPanturrilhaMedial() {
		return panturrilhaMedial;
	}

	public BigDecimal getAxilarMedia() {
		return axilarMedia;
	}
}
