package br.com.nutriclinic.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nutriclinic.api.form.AtendimentoPacienteForm;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AtendimentoRepository;
import br.com.nutriclinic.domain.repository.NutricionistaRepository;
import br.com.nutriclinic.domain.repository.PacienteRepository;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.repository.entity.Nutricionista;
import br.com.nutriclinic.domain.repository.entity.Paciente;

@Service
public class AtendimentoService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Transactional
	public Atendimento iniciarAtendimento(AtendimentoPacienteForm atendimentoPacienteForm, UsuarioAutenticado usuarioAutenticado) {
		
		Optional<Nutricionista> nutricionista = nutricionistaRepository.findById(usuarioAutenticado.getId());
		if (nutricionista.isEmpty()) {
			throw new NegocioException("Usuário logado não é nutricionista");
		}
		
		Paciente paciente = new Paciente();
		paciente.setNome(atendimentoPacienteForm.getNome());
		paciente.setDataNascimento(atendimentoPacienteForm.getDataNascimento());
		paciente.setProfissao(atendimentoPacienteForm.getProfissao());
		paciente.setSexo(atendimentoPacienteForm.getSexo());
		paciente.setNutricionista(nutricionista.get());
		pacienteRepository.save(paciente);
		
		Atendimento novoAtendimento = new Atendimento();
		novoAtendimento.setDataAtendimento(LocalDateTime.now());
		novoAtendimento.setNutricionista(nutricionista.get());
		novoAtendimento.setAnamnese(atendimentoPacienteForm.getAnamnese());
		
		atendimentoRepository.save(novoAtendimento);
		
		return novoAtendimento;
	}

}
