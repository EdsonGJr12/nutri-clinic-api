package br.com.nutriclinic.domain.enuns;

public enum DiaSemana {
	DOMINGO(1, "Domingo"),
	SEGUNDA(2, "Segunda"),
	TERCA(3, "Terça"),
	QUARTA(4, "Quarta"),
	QUINTA(5, "Quinta"),
	SEXTA(6, "Sexta"),
	SABADO(7, "Sábado");

	private int diaSemana;
	private String descricaoDiaSemana;

	DiaSemana(int diaSemana, String descricaoDiaSemana) {
		this.diaSemana = diaSemana;
		this.descricaoDiaSemana = descricaoDiaSemana;
	}
	
	public int getDiaSemana() {
		return diaSemana;
	}
	
	public String getDescricaoDiaSemana() {
		return descricaoDiaSemana;
	}
}
