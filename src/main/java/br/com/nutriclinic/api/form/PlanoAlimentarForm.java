package br.com.nutriclinic.api.form;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PlanoAlimentarForm {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private Boolean segunda;
	
	@NotNull
	private Boolean terca;
	
	@NotNull
	private Boolean quarta;
	
	@NotNull
	private Boolean quinta;
	
	@NotNull
	private Boolean sexta;
	
	@NotNull
	private Boolean sabado;
	
	@NotNull
	private Boolean domingo;
	
	@NotNull
	@NotEmpty
	@Valid
	private List<RefeicaoForm> refeicoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getTerca() {
		return terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	public List<RefeicaoForm> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(List<RefeicaoForm> refeicoes) {
		this.refeicoes = refeicoes;
	}

}
