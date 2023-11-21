package br.com.nutriclinic.api.form;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AlertaHidratacaoHorarioForm {
	private Long idUsuario;

	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
	private LocalTime horario;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
}
