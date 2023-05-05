package br.com.nutriclinic.api.model;

public class AuthTokenModel {
	private String token;
	private String nomeUsuario;
	private String perfilUsuario;

	public AuthTokenModel(String token, String nomeUsuario, String perfilUsuario) {
		this.token = token;
		this.nomeUsuario = nomeUsuario;
		this.perfilUsuario = perfilUsuario;
	}

	public String getToken() {
		return token;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public String getPerfilUsuario() {
		return perfilUsuario;
	}
}
