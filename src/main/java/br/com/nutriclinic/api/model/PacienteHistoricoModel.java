package br.com.nutriclinic.api.model;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import br.com.nutriclinic.domain.repository.entity.PacienteHistorico;

public class PacienteHistoricoModel {

	private Long id;
	private String dataExtenso;
	private String descricaoOcorrencia;
	private String usuarioOcorrencia;

	public PacienteHistoricoModel(PacienteHistorico ocorrencia) {
		this.dataExtenso = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(ocorrencia.getDataOcorrencia());
		this.descricaoOcorrencia = ocorrencia.getOcorrencia().getDescricao();
		this.usuarioOcorrencia = ocorrencia.getUsuario().getNome();
	}

	public String getDataExtenso() {
		return dataExtenso;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public String getUsuarioOcorrencia() {
		return usuarioOcorrencia;
	}

	public Long getId() {
		return id;
	}

}
