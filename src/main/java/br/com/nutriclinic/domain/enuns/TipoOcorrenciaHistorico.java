package br.com.nutriclinic.domain.enuns;

public enum TipoOcorrenciaHistorico {
	ATENDIMENTO_NUTRICIONISTA("Atendimento com nutricionista"),
	ATENDIMENTO_NUTROLOGO("Atendimento com nutrólogo"),
	ATENDIMENTO_ENFERMAGEM("Atendimento na enfermagem"),
	REGISTRO_ACOMPANHAMENTO("Registro de acompanhamento");

	private String descricao;

	TipoOcorrenciaHistorico(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
