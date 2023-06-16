package br.com.nutriclinic.api.model;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.nutriclinic.domain.enuns.Sexo;
import br.com.nutriclinic.domain.repository.entity.Paciente;

public class PacienteModel {
	private Long id;
	private String nome;
	private Sexo sexo;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private Integer idade;
	private String profissao;

	public PacienteModel(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.sexo = paciente.getSexo();
		this.dataNascimento = paciente.getDataNascimento();
		this.idade = Period.between(paciente.getDataNascimento(), LocalDate.now()).getYears();
		this.profissao = paciente.getProfissao();
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

	public Integer getIdade() {
		return idade;
	}

	public String getProfissao() {
		return profissao;
	}

}
