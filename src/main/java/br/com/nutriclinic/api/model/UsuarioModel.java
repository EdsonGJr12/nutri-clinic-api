package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.Usuario;

public class UsuarioModel {

	private Long id;
	private String nome;
	private String cpf;

	public UsuarioModel(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getLogin();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
}
