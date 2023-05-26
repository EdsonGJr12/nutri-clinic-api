package br.com.nutriclinic.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nutriclinic.api.form.AtendimentoPacienteForm;
import br.com.nutriclinic.api.form.AvaliacaoFisicaForm;
import br.com.nutriclinic.api.model.AvaliacaoFisicaModel;
import br.com.nutriclinic.api.model.ImcModel;
import br.com.nutriclinic.api.model.ResultadoAvaliacaoFisicaModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AtendimentoRepository;
import br.com.nutriclinic.domain.repository.AvaliacaoFisicaRepository;
import br.com.nutriclinic.domain.repository.NutricionistaRepository;
import br.com.nutriclinic.domain.repository.PacienteRepository;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.repository.entity.AvaliacaoFisica;
import br.com.nutriclinic.domain.repository.entity.Circunferencia;
import br.com.nutriclinic.domain.repository.entity.ComposicaoCorporal;
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
	
	@Autowired
	private AvaliacaoFisicaRepository avaliacaoFisicaRepository;
	
	@Autowired
	private IMCService imcService;

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
		pacienteRepository.save(paciente);
		
		Atendimento atendimento = new Atendimento();
		atendimento.setDataAtendimento(LocalDateTime.now());
		atendimento.setNutricionista(nutricionista.get());
		atendimento.setPaciente(paciente);
		atendimento.setAnamnese(atendimentoPacienteForm.getAnamnese());
		
		atendimentoRepository.save(atendimento);
		
		return atendimento;
	}

	@Transactional
	public AvaliacaoFisicaModel registrarAvaliacaoFisica(Long idAtendimento, AvaliacaoFisicaForm avaliacaoFisicaForm) {
		Atendimento atendimento = atendimentoRepository.findById(idAtendimento)
			.orElseThrow(() -> new NegocioException("Atendimento não encontrado"));
		
		AvaliacaoFisica avaliacaoFisica = getAvaliacaoFisica(avaliacaoFisicaForm);
		avaliacaoFisica.setPaciente(atendimento.getPaciente());
		avaliacaoFisicaRepository.save(avaliacaoFisica);
		atendimento.setAvaliacaoFisica(avaliacaoFisica);
		
		List<ResultadoAvaliacaoFisicaModel> resultados = new ArrayList<>();
		
		ImcModel imcModel = imcService.classificarImc(avaliacaoFisica.getPeso(), avaliacaoFisica.getAltura());
		ResultadoAvaliacaoFisicaModel resultadoImc = getResultadoImc(imcModel);
		
		resultados.add(resultadoImc);
		return new AvaliacaoFisicaModel(avaliacaoFisica, resultados);
	}

	private ResultadoAvaliacaoFisicaModel getResultadoImc(ImcModel imcModel) {
		ResultadoAvaliacaoFisicaModel resultadoImc = new ResultadoAvaliacaoFisicaModel();
		resultadoImc.setParametro("IMC");
		resultadoImc.setValorAtual(imcModel.getValor());
		resultadoImc.setRecomendacao(imcModel.getRecomendacao());
		resultadoImc.setSituacao(imcModel.getSituacao());
		
		return resultadoImc;
	}

	private AvaliacaoFisica getAvaliacaoFisica(AvaliacaoFisicaForm avaliacaoFisicaForm) {
		AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
		avaliacaoFisica.setAltura(avaliacaoFisicaForm.getAltura());
		avaliacaoFisica.setPeso(avaliacaoFisicaForm.getPeso());
		
		Circunferencia circunferencia = getCircunferencia(avaliacaoFisicaForm);
		avaliacaoFisica.setCircunferencia(circunferencia);	
		
		ComposicaoCorporal composicaoCorporal = new ComposicaoCorporal();
		composicaoCorporal.setTipo(avaliacaoFisicaForm.getTipoComposicaoCorporal());
		composicaoCorporal.setProtocolo(avaliacaoFisicaForm.getProtocolo());
		composicaoCorporal.setBiceps(avaliacaoFisicaForm.getBiceps());
		composicaoCorporal.setAbdominal(avaliacaoFisicaForm.getAbdominal());
		composicaoCorporal.setTriceps(avaliacaoFisicaForm.getTriceps());
		composicaoCorporal.setSuprailiaca(avaliacaoFisicaForm.getSuprailiaca());
		composicaoCorporal.setAxilarMedia(avaliacaoFisicaForm.getAxilarMedia());
		composicaoCorporal.setSubscapular(avaliacaoFisicaForm.getSubscapular());
		composicaoCorporal.setTorax(avaliacaoFisicaForm.getTorax());
		composicaoCorporal.setCoxa(avaliacaoFisicaForm.getCoxa());
		composicaoCorporal.setPanturrilhaMedial(avaliacaoFisicaForm.getPanturrilhaMedial());
		
		avaliacaoFisica.setComposicaoCorporal(composicaoCorporal);
		
		return avaliacaoFisica;
	}

	private Circunferencia getCircunferencia(AvaliacaoFisicaForm avaliacaoFisicaForm) {
		Circunferencia circunferencia = new Circunferencia();
		circunferencia.setBracoEsquerdoRelaxado(avaliacaoFisicaForm.getBracoEsquerdoRelaxado());
		circunferencia.setBracoDireitoRelaxado(avaliacaoFisicaForm.getBracoDireitoRelaxado());
		circunferencia.setBracoEsquerdoContraido(avaliacaoFisicaForm.getBracoEsquerdoContraido());
		circunferencia.setBracoDireitoContraido(avaliacaoFisicaForm.getBracoDireitoContraido());
		circunferencia.setAntebracoEsquerdo(avaliacaoFisicaForm.getAntebracoEsquerdo());
		circunferencia.setAntebracoDireito(avaliacaoFisicaForm.getAntebracoDireito());
		circunferencia.setPunhoEsquerdo(avaliacaoFisicaForm.getPunhoEsquerdo());
		circunferencia.setPunhoDireito(avaliacaoFisicaForm.getPunhoDireito());
		return circunferencia;
	}

}
