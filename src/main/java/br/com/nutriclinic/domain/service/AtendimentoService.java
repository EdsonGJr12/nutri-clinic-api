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
import br.com.nutriclinic.api.form.PlanoAlimentarForm;
import br.com.nutriclinic.api.form.RefeicaoAlimentoForm;
import br.com.nutriclinic.api.form.RefeicaoForm;
import br.com.nutriclinic.api.model.AvaliacaoFisicaModel;
import br.com.nutriclinic.api.model.ImcModel;
import br.com.nutriclinic.api.model.PlanoAlimentarModel;
import br.com.nutriclinic.api.model.ResultadoAvaliacaoFisicaModel;
import br.com.nutriclinic.config.UsuarioAutenticado;
import br.com.nutriclinic.domain.enuns.TipoOcorrenciaHistorico;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.AtendimentoRepository;
import br.com.nutriclinic.domain.repository.AvaliacaoFisicaRepository;
import br.com.nutriclinic.domain.repository.NutricionistaRepository;
import br.com.nutriclinic.domain.repository.PacienteHistoricoRepository;
import br.com.nutriclinic.domain.repository.PacienteRepository;
import br.com.nutriclinic.domain.repository.PlanoAlimentarRepository;
import br.com.nutriclinic.domain.repository.entity.Alimento;
import br.com.nutriclinic.domain.repository.entity.Atendimento;
import br.com.nutriclinic.domain.repository.entity.AvaliacaoFisica;
import br.com.nutriclinic.domain.repository.entity.Circunferencia;
import br.com.nutriclinic.domain.repository.entity.ComposicaoCorporal;
import br.com.nutriclinic.domain.repository.entity.Medida;
import br.com.nutriclinic.domain.repository.entity.Nutricionista;
import br.com.nutriclinic.domain.repository.entity.Paciente;
import br.com.nutriclinic.domain.repository.entity.PacienteHistorico;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;
import br.com.nutriclinic.domain.repository.entity.Refeicao;
import br.com.nutriclinic.domain.repository.entity.RefeicaoAlimento;
import br.com.nutriclinic.domain.repository.entity.Usuario;

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
	private PlanoAlimentarRepository planoAlimentarRepository;
	
	@Autowired
	private PacienteHistoricoRepository pacienteHistoricoRepository;
	
	@Autowired
	private IMCService imcService;

	@Transactional
	public Atendimento iniciarAtendimento(AtendimentoPacienteForm atendimentoPacienteForm, UsuarioAutenticado usuarioAutenticado) {
		
		Optional<Nutricionista> nutricionista = nutricionistaRepository.findById(usuarioAutenticado.getId());
		if (nutricionista.isEmpty()) {
			throw new NegocioException("Usuário logado não é nutricionista");
		}
		
		Paciente paciente = getPaciente(atendimentoPacienteForm);
		pacienteRepository.save(paciente);
		
		Atendimento atendimento = new Atendimento();
		atendimento.setDataAtendimento(LocalDateTime.now());
		atendimento.setNutricionista(nutricionista.get());
		atendimento.setPaciente(paciente);
		atendimento.setAnamnese(atendimentoPacienteForm.getAnamnese());
		atendimentoRepository.save(atendimento);
		
		return atendimento;
	}

	private Paciente getPaciente(AtendimentoPacienteForm atendimentoPacienteForm) {
		Paciente paciente = new Paciente();
		paciente.setNome(atendimentoPacienteForm.getNome());
		paciente.setDataNascimento(atendimentoPacienteForm.getDataNascimento());
		paciente.setProfissao(atendimentoPacienteForm.getProfissao());
		paciente.setSexo(atendimentoPacienteForm.getSexo());
		return paciente;
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

	@Transactional
	public PlanoAlimentarModel registrarPlanoAlimentar(Long idAtendimento, PlanoAlimentarForm planoAlimentarForm, UsuarioAutenticado usuarioAutenticado) {
		
		Atendimento atendimento = atendimentoRepository.findById(idAtendimento)
				.orElseThrow(() -> new NegocioException("Atendimento não encontrado"));
		
		if (atendimento.getPlanoAlimentar() != null) {
			throw new NegocioException("Atendimento já possui um plano alimentar");
		}
		
		PlanoAlimentar planoAlimentar = getPlanoAlimentar(planoAlimentarForm);
		planoAlimentar.setPaciente(atendimento.getPaciente());
		planoAlimentarRepository.save(planoAlimentar);
		
		atendimento.setPlanoAlimentar(planoAlimentar);
		
		PacienteHistorico ocorrencia = getOcorrenciaAtendimento(atendimento, usuarioAutenticado);
		
		pacienteHistoricoRepository.save(ocorrencia);
		
		return new PlanoAlimentarModel(planoAlimentar);
	}
	
	private PacienteHistorico getOcorrenciaAtendimento(Atendimento atendimento, UsuarioAutenticado usuarioAutenticado) {
		PacienteHistorico ocorrencia = new PacienteHistorico();
		ocorrencia.setDataOcorrencia(LocalDateTime.now());
		ocorrencia.setUsuario(new Usuario(usuarioAutenticado.getId()));
		ocorrencia.setPaciente(atendimento.getPaciente());
		ocorrencia.setOcorrencia(TipoOcorrenciaHistorico.ATENDIMENTO_NUTRICIONISTA);
		
		return ocorrencia;
	}

	private PlanoAlimentar getPlanoAlimentar(PlanoAlimentarForm planoAlimentarForm) {
		PlanoAlimentar planoAlimentar = new PlanoAlimentar();
		
		planoAlimentar.setDescricao(planoAlimentarForm.getDescricao());
		planoAlimentar.setSegunda(planoAlimentarForm.getSegunda());
		planoAlimentar.setTerca(planoAlimentarForm.getTerca());
		planoAlimentar.setQuarta(planoAlimentarForm.getQuarta());
		planoAlimentar.setQuinta(planoAlimentarForm.getQuinta());
		planoAlimentar.setSexta(planoAlimentarForm.getSexta());
		planoAlimentar.setSabado(planoAlimentarForm.getSabado());
		planoAlimentar.setDomingo(planoAlimentarForm.getDomingo());
		
		List<Refeicao> refeicoes = getRefeicoes(planoAlimentarForm);
		
		planoAlimentar.setRefeicoes(refeicoes);
		
		return planoAlimentar;
	}

	private List<Refeicao> getRefeicoes(PlanoAlimentarForm planoAlimentarForm) {
		List<Refeicao> refeicoes = planoAlimentarForm.getRefeicoes().stream()
			.map(this::getRefeicao)
			.toList();
		return refeicoes;
	}

	private Refeicao getRefeicao(RefeicaoForm refeicaoForm) {
		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao(refeicaoForm.getDescricao());
		refeicao.setHorario(refeicaoForm.getHorario());
		
		List<RefeicaoAlimento> alimentos = getAlimentos(refeicaoForm);
		
		refeicao.setAlimentos(alimentos);
		
		return refeicao;
	}

	private List<RefeicaoAlimento> getAlimentos(RefeicaoForm refeicaoForm) {
		List<RefeicaoAlimento> alimentos = refeicaoForm.getAlimentos().stream()
			.map(this::getAlimento)
			.toList();
		return alimentos;
	}

	private RefeicaoAlimento getAlimento(RefeicaoAlimentoForm alimentoForm) {
		RefeicaoAlimento alimento = new RefeicaoAlimento();
		alimento.setAlimento(new Alimento(alimentoForm.getIdAlimento()));
		alimento.setQuantidade(alimentoForm.getQuantidade());
		alimento.setMedida(new Medida(alimentoForm.getIdMedida()));
		
		return alimento;
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
