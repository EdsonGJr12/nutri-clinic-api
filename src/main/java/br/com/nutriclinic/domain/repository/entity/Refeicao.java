package br.com.nutriclinic.domain.repository.entity;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Refeicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalTime horario;

	private String descricao;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_refeicao")
	private List<RefeicaoAlimento> alimentos;
	
	private String observacao;

	public Refeicao() {}

	public Refeicao(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Refeicao other = (Refeicao) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<RefeicaoAlimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<RefeicaoAlimento> alimentos) {
		this.alimentos = alimentos;
	}

	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void adicionarRefeicao(RefeicaoAlimento refeicaoAlimento) {
		this.alimentos.add(refeicaoAlimento);
	}

	public void removerAlimento(Long idRefeicaoAlimento) {
		this.alimentos.remove(new RefeicaoAlimento(idRefeicaoAlimento));
	}
}
