package br.com.nutriclinic.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;

@Repository
public interface PlanoAlimentarRepository extends JpaRepository<PlanoAlimentar, Long> {

	Optional<PlanoAlimentar> findFirstByPacienteIdOrderByDataHoraInclusaoDesc(Long idPaciente);
	
}
