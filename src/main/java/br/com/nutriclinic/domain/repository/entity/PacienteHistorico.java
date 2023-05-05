package br.com.nutriclinic.domain.repository.entity;

import java.time.LocalDate;

import br.com.nutriclinic.domain.enuns.TipoOcorrenciaHistorico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PacienteHistorico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private LocalDate dataOcorrencia;
	
	@Enumerated(EnumType.STRING)
	private TipoOcorrenciaHistorico ocorrencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDate dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public TipoOcorrenciaHistorico getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(TipoOcorrenciaHistorico ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

}
