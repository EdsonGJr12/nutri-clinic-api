package br.com.nutriclinic.api.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.nutriclinic.domain.enuns.Sexo;
import br.com.nutriclinic.domain.repository.entity.Paciente;

public class PacienteModel {
	private Long id;
	private String nome;
	private Sexo sexo;
	private LocalDate dataNascimento;
	private Long idade;

	public PacienteModel(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.sexo = paciente.getSexo();
		this.dataNascimento = paciente.getDataNascimento();
		this.idade = ChronoUnit.YEARS.between(paciente.getDataNascimento(), LocalDate.now());
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
	
	public Long getIdade() {
		return idade;
	}

}
