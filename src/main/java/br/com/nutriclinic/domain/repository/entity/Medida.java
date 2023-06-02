package br.com.nutriclinic.domain.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	
	private String descricaoApresentacao;
	
	public Medida() {}

	public Medida(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricaoApresentacao() {
		return descricaoApresentacao;
	}
	
	public void setDescricaoApresentacao(String descricaoApresentacao) {
		this.descricaoApresentacao = descricaoApresentacao;
	}

}
