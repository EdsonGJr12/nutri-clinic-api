package br.com.nutriclinic.api.form;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RefeicaoForm {

	@NotBlank
	private String descricao;

	@NotNull
	private LocalTime horario;

	@NotNull
	@NotEmpty
	private List<RefeicaoAlimentoForm> alimentos;
	
	private String observacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public List<RefeicaoAlimentoForm> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<RefeicaoAlimentoForm> alimentos) {
		this.alimentos = alimentos;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
