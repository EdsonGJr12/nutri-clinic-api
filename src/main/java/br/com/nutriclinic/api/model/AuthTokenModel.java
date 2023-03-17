package br.com.nutriclinic.api.model;

public class AuthTokenModel {
	private String token;

	public AuthTokenModel(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
