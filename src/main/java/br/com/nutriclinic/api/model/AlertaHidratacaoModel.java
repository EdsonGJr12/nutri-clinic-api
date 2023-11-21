package br.com.nutriclinic.api.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.nutriclinic.domain.repository.entity.AlertaHidratacao;

public class AlertaHidratacaoModel {
	
	private Long id;

	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
	private LocalTime horario;

	public AlertaHidratacaoModel(AlertaHidratacao alertaHidratacao) {
		this.id = alertaHidratacao.getId();
		this.horario = alertaHidratacao.getHorario();
	}

	public Long getId() {
		return id;
	}

	public LocalTime getHorario() {
		return horario;
	}
}
