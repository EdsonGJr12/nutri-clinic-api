package br.com.nutriclinic.domain.form;

import java.math.BigDecimal;

import br.com.nutriclinic.domain.enuns.Sexo;

public class Faulker4PregasForm {
	private BigDecimal triceps;
	private BigDecimal subescapular;
	private BigDecimal suprailiaca;
	private BigDecimal abdominal;
	private Sexo sexo;
	private Integer idade;

	public BigDecimal getTriceps() {
		return triceps;
	}

	public void setTriceps(BigDecimal triceps) {
		this.triceps = triceps;
	}

	public BigDecimal getSubescapular() {
		return subescapular;
	}

	public void setSubescapular(BigDecimal subescapular) {
		this.subescapular = subescapular;
	}

	public BigDecimal getSuprailiaca() {
		return suprailiaca;
	}

	public void setSuprailiaca(BigDecimal suprailiaca) {
		this.suprailiaca = suprailiaca;
	}

	public BigDecimal getAbdominal() {
		return abdominal;
	}

	public void setAbdominal(BigDecimal abdominal) {
		this.abdominal = abdominal;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
