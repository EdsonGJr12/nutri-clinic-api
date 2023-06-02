package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.RefeicaoAlimento;

public class RefeicaoAlimentoModel {
	
	private Long id;
	private String descricao;
	
	public RefeicaoAlimentoModel(RefeicaoAlimento refeicaoAlimento) {
		this.id = refeicaoAlimento.getId();
		this.descricao = String.format("%s(%s %s)", refeicaoAlimento.getAlimento().getDescricao(), 
				refeicaoAlimento.getQuantidade(), refeicaoAlimento.getMedida().getDescricaoApresentacao());
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
