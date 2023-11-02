package br.com.nutriclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.PlanoAlimentarDiaSemana;

@Repository
public interface PlanoAlimentarDiaSemanaRepository extends JpaRepository<PlanoAlimentarDiaSemana, Long> {

}
