package br.com.nutriclinic.domain.model;

public class Notificacao {
	private String titulo;
	private String corpo;

	public Notificacao(String titulo, String corpo) {
		this.titulo = titulo;
		this.corpo = corpo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCorpo() {
		return corpo;
	}
}
