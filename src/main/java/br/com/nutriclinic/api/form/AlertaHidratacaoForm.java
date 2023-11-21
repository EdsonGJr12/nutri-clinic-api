package br.com.nutriclinic.api.form;

import java.time.LocalTime;
import java.util.List;

public class AlertaHidratacaoForm {

	private Long idUsuario;
	private List<LocalTime> horarios;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<LocalTime> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<LocalTime> horarios) {
		this.horarios = horarios;
	}

}
