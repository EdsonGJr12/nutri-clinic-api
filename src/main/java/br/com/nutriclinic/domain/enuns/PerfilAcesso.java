package br.com.nutriclinic.domain.enuns;

public enum PerfilAcesso {
	NUTRICIONISTA("Nutricionista"),
	NUTROLOGO("Nutrólogo"),
	ENFERMEIRA("Enfermeira"),
	PACIENTE("Paciente");

	private String descricao;

	PerfilAcesso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
