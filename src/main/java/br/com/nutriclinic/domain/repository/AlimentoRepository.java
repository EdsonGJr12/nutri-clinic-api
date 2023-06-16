package br.com.nutriclinic.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

	List<Alimento> findByDescricaoContaining(String descricao);

}
