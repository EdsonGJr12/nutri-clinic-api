package br.com.nutriclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}
