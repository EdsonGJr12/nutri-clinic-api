package br.com.nutriclinic.api.form;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class RefeicaoAlimentoForm {
	
	@NotNull
	private Long idAlimento;
	
	@NotNull
	private BigDecimal quantidade;
	
	@NotNull
	private Long idMedida;

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	
	public Long getIdMedida() {
		return idMedida;
	}
	
	public void setIdMedida(Long idMedida) {
		this.idMedida = idMedida;
	}

}
