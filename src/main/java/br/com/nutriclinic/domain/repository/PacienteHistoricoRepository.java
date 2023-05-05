package br.com.nutriclinic.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.PacienteHistorico;

@Repository
public interface PacienteHistoricoRepository extends JpaRepository<PacienteHistorico, Long> {

	List<PacienteHistorico> findByPacienteId(Long idPaciente);
	
}
