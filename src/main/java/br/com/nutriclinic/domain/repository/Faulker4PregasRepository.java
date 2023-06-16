package br.com.nutriclinic.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.enuns.Sexo;
import br.com.nutriclinic.domain.repository.entity.Faulker4Pregas;

@Repository
public interface Faulker4PregasRepository extends JpaRepository<Faulker4Pregas, Integer> {
	List<Faulker4Pregas> findBySexo(Sexo sexo);
}
