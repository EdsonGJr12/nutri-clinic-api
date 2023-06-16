package br.com.nutriclinic.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.nutriclinic.domain.repository.entity.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	@Query("from Atendimento a "
			+ "where a.paciente.id = :idPaciente "
			+ "and (a.avaliacaoFisica is null or a.planoAlimentar is null)")
	Optional<Atendimento> findAtendimentoEmAndamento(Long idPaciente);

}
