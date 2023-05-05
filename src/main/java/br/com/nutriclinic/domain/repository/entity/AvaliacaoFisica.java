package br.com.nutriclinic.domain.repository.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class AvaliacaoFisica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	private BigDecimal altura;

	private BigDecimal peso;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_circunferencia")
	private Circunferencia circunferencia;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_composicao_corporal")
	private ComposicaoCorporal composicaoCorporal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

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

	public Circunferencia getCircunferencia() {
		return circunferencia;
	}

	public void setCircunferencia(Circunferencia circunferencia) {
		this.circunferencia = circunferencia;
	}

	public ComposicaoCorporal getComposicaoCorporal() {
		return composicaoCorporal;
	}

	public void setComposicaoCorporal(ComposicaoCorporal composicaoCorporal) {
		this.composicaoCorporal = composicaoCorporal;
	}

}
