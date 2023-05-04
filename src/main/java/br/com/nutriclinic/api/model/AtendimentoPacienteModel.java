package br.com.nutriclinic.api.model;

import br.com.nutriclinic.domain.repository.entity.Atendimento;

public class AtendimentoPacienteModel {
	private Long id;

	public AtendimentoPacienteModel(Atendimento novoAtendimento) {
		this.id = novoAtendimento.getId();
	}

	public Long getId() {
		return id;
	}

}
