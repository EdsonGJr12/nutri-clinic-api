package br.com.nutriclinic.api.model;

import java.time.LocalDate;

import br.com.nutriclinic.domain.enums.Sexo;
import br.com.nutriclinic.domain.repository.entity.Paciente;

public class PacienteModel {
	private Long id;
	private String nome;
	private Sexo sexo;
	private LocalDate dataNascimento;

	public PacienteModel(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.sexo = paciente.getSexo();
		this.dataNascimento = paciente.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}
