package br.com.nutriclinic.api.model;

public class AuthTokenModel {
	private String accessToken;

	public AuthTokenModel(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
}
