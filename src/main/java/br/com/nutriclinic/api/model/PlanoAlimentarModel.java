package br.com.nutriclinic.api.model;

import java.util.List;

import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;

public class PlanoAlimentarModel {
	private Long id;
	private String descricao;
	private Boolean segunda;
	private Boolean terca;
	private Boolean quarta;
	private Boolean quinta;
	private Boolean sexta;
	private Boolean sabado;
	private Boolean domingo;

	private List<RefeicaoModel> refeicoes;

	public PlanoAlimentarModel(PlanoAlimentar planoAlimentar) {
		this.id = planoAlimentar.getId();
		this.descricao = planoAlimentar.getDescricao();
		this.segunda = planoAlimentar.getSegunda();
		this.terca = planoAlimentar.getTerca();
		this.quarta = planoAlimentar.getQuarta();
		this.quinta = planoAlimentar.getQuinta();
		this.sexta = planoAlimentar.getSexta();
		this.sabado = planoAlimentar.getSabado();
		this.domingo = planoAlimentar.getDomingo();

		this.refeicoes = planoAlimentar.getRefeicoes()
				.stream().map(RefeicaoModel::new)
				.toList();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public Boolean getTerca() {
		return terca;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public List<RefeicaoModel> getRefeicoes() {
		return refeicoes;
	}

}
