package br.com.nutriclinic.domain.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.AlertaHidratacao;

@Repository
public interface AlertaHidratacaoRepository extends JpaRepository<AlertaHidratacao, Long> {
	List<AlertaHidratacao> findByUsuarioId(Long idUsuario);

	List<AlertaHidratacao> findByHorario(LocalTime agora);
}
