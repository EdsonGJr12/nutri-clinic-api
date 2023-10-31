package br.com.nutriclinic.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.form.PacienteAtualizacaoForm;
import br.com.nutriclinic.api.model.DiaSemanaModel;
import br.com.nutriclinic.api.model.PacienteModel;
import br.com.nutriclinic.api.model.PlanoAlimentarModel;
import br.com.nutriclinic.api.model.RefeicaoDiaModel;
import br.com.nutriclinic.api.model.RefeicaoDiaResumoModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.PacienteRepository;
import br.com.nutriclinic.domain.repository.PlanoAlimentarRepository;
import br.com.nutriclinic.domain.repository.entity.Paciente;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;
import br.com.nutriclinic.domain.repository.entity.Refeicao;
import br.com.nutriclinic.domain.repository.entity.RefeicaoAlimento;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin
public class PacienteController {
	
	@Autowired
	private PlanoAlimentarRepository planoAlimentarRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping("/{idPaciente}")
	public PacienteModel buscarPacientePorId(@PathVariable Long idPaciente) {
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
		if (paciente.isPresent()) {
			return new PacienteModel(paciente.get());
		} else {
			throw new NegocioException("Paciente não encontrado");
		}
	}
	
	@GetMapping("/cpf/{cpf}")
	public PacienteModel buscarPaciente(@PathVariable String cpf) {
		Optional<Paciente> paciente = pacienteRepository.findByCpf(cpf);
		if (paciente.isPresent()) {
			return new PacienteModel(paciente.get());
		} else {
			throw new NegocioException("Paciente não encontrado");
		}
	}
	
	@PutMapping("/{idPaciente}")
	@Transactional
	public void atualizarDadosPaciente(@PathVariable Long idPaciente, @RequestBody PacienteAtualizacaoForm pacienteForm) {
		Optional<Paciente> pacienteOp = pacienteRepository.findById(idPaciente);
		if (pacienteOp.isPresent()) {
			Paciente paciente = pacienteOp.get();
			paciente.setDataNascimento(pacienteForm.getDataNascimento());
			paciente.setNome(pacienteForm.getNome());
			paciente.setProfissao(pacienteForm.getProfissao());
			paciente.setSexo(pacienteForm.getSexo());
		} else {
			throw new NegocioException("Paciente não encontrado");
		}
	}
	
	
	@GetMapping("/{idPaciente}/ultimo-plano")
	public PlanoAlimentarModel buscarUltimoPlanoAlimentar(@PathVariable Long idPaciente) {
		Optional<PlanoAlimentar> planoAlimentar = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentar.isPresent()) {
			return new PlanoAlimentarModel(planoAlimentar.get());
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
	}
	
	@GetMapping("/{idPaciente}/ultimo-plano/dias-semana")
	public List<DiaSemanaModel> buscarDiasNaSemanaComPlano(@PathVariable Long idPaciente) {
		List<DiaSemanaModel> diasSemana = new ArrayList<>();
		
		Optional<PlanoAlimentar> planoAlimentarOp = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentarOp.isPresent()) {
			PlanoAlimentar planoAlimentar = planoAlimentarOp.get();
			if (planoAlimentar.getSegunda()) {
				DiaSemanaModel segunda = new DiaSemanaModel();
				segunda.setCodigo(2);
				segunda.setDiaSemana("Segunda");
				segunda.setDiaSemanaResumida("SEG");
				diasSemana.add(segunda);
			}
			if (planoAlimentar.getTerca()) {
				DiaSemanaModel terca = new DiaSemanaModel();
				terca.setCodigo(3);
				terca.setDiaSemana("Terça");
				terca.setDiaSemanaResumida("TER");
				diasSemana.add(terca);
			}
			if (planoAlimentar.getQuarta()) {
				DiaSemanaModel quarta = new DiaSemanaModel();
				quarta.setCodigo(4);
				quarta.setDiaSemana("Quarta");
				quarta.setDiaSemanaResumida("QUA");
				diasSemana.add(quarta);
			}
			if (planoAlimentar.getQuinta()) {
				DiaSemanaModel quinta = new DiaSemanaModel();
				quinta.setCodigo(4);
				quinta.setDiaSemana("Quinta");
				quinta.setDiaSemanaResumida("QUI");
				diasSemana.add(quinta);
			}
			if (planoAlimentar.getSexta()) {
				DiaSemanaModel sexta = new DiaSemanaModel();
				sexta.setCodigo(4);
				sexta.setDiaSemana("Sexta");
				sexta.setDiaSemanaResumida("SEX");
				diasSemana.add(sexta);
			}
			if (planoAlimentar.getSabado()) {
				DiaSemanaModel sabado = new DiaSemanaModel();
				sabado.setCodigo(4);
				sabado.setDiaSemana("Sábado");
				sabado.setDiaSemanaResumida("SAB");
				diasSemana.add(sabado);
			}
			if (planoAlimentar.getDomingo()) {
				DiaSemanaModel domingo = new DiaSemanaModel();
				domingo.setCodigo(4);
				domingo.setDiaSemana("Domingo");
				domingo.setDiaSemanaResumida("DOM");
				diasSemana.add(domingo);
			}
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
		
		return diasSemana;
	}
	
	@GetMapping("/{idPaciente}/ultimo-plano/dias-semana/{diaSemana}/refeicoes")
	public RefeicaoDiaModel buscarRefeicoes(@PathVariable Long idPaciente, @PathVariable Integer diaSemana) {
		RefeicaoDiaModel refeicaoDiaModel = new RefeicaoDiaModel();
		
		Optional<PlanoAlimentar> planoAlimentarOp = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentarOp.isPresent()) {
			PlanoAlimentar planoAlimentar = planoAlimentarOp.get();
			
//			List<RefeicaoDiaResumoModel> refeicoes = planoAlimentar.getDias().stream().map(dia -> {
//				RefeicaoDiaResumoModel refeicaoDia = new RefeicaoDiaResumoModel();
//				refeicaoDia.setId(refeicao.getId());
//				refeicaoDia.setDescricao(refeicao.getDescricao());
//				refeicaoDia.setHorario(refeicao.getHorario());
//				return refeicaoDia;
//			}).collect(Collectors.toList());
			
			List<RefeicaoDiaResumoModel> refeicoes = planoAlimentar.getDias().stream()
				.filter(dia -> dia.getDiaSemana().equals(diaSemana))
				.findFirst()
				.get()
				.getRefeicoes()
				.stream()
				.map(refeicao -> {
					RefeicaoDiaResumoModel refeicaoDia = new RefeicaoDiaResumoModel();
					refeicaoDia.setId(refeicao.getId());
					refeicaoDia.setDescricao(refeicao.getDescricao());
					refeicaoDia.setHorario(refeicao.getHorario());
					return refeicaoDia;
				}).collect(Collectors.toList());
			
			refeicaoDiaModel.setRefeicoes(refeicoes);
			
			if (diaSemana.equals(2)) {
				refeicaoDiaModel.setDiaSemana("Segunda");
			}
			if (diaSemana.equals(3)) {
				refeicaoDiaModel.setDiaSemana("Terça");
			}
			if (diaSemana.equals(4)) {
				refeicaoDiaModel.setDiaSemana("Quarta");
			}
			if (diaSemana.equals(5)) {
				refeicaoDiaModel.setDiaSemana("Quinta");
			}
			if (diaSemana.equals(6)) {
				refeicaoDiaModel.setDiaSemana("Sexta");
			}
			if (diaSemana.equals(7)) {
				refeicaoDiaModel.setDiaSemana("Sábado");
			}
			if (diaSemana.equals(1)) {
				refeicaoDiaModel.setDiaSemana("Domingo");
			}
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
		
		return refeicaoDiaModel;
	}
	
	@GetMapping("/{idPaciente}/ultimo-plano/dias-semana/{diaSemana}/refeicoes/{idRefeicao}/alimentos")
	public List<String> buscarAlimentosDaRefeicao(@PathVariable Long idPaciente, @PathVariable Integer diaSemana, @PathVariable Long idRefeicao) {
		List<String> alimentos = new ArrayList<>();
		
		Optional<PlanoAlimentar> planoAlimentarOp = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentarOp.isPresent()) {
			PlanoAlimentar planoAlimentar = planoAlimentarOp.get();
			
			if (diaSemana == 1 && !planoAlimentar.getDomingo()) {
				return alimentos;
			}
			
			if (diaSemana == 2 && !planoAlimentar.getSegunda()) {
				return alimentos;
			}
			
			if (diaSemana == 3 && !planoAlimentar.getTerca()) {
				return alimentos;
			}
			
			if (diaSemana == 4 && !planoAlimentar.getQuarta()) {
				return alimentos;
			}
			
			if (diaSemana == 5 && !planoAlimentar.getQuinta()) {
				return alimentos;
			}
			
			if (diaSemana == 6 && !planoAlimentar.getSexta()) {
				return alimentos;
			}
			
			Refeicao refeicaoEncontrada = planoAlimentar.getDias().stream()
					.filter(dia -> dia.getDiaSemana().equals(diaSemana))
					.findFirst()
					.get()
					.getRefeicoes().stream()
				.filter(refeicao -> refeicao.getId().equals(idRefeicao))
				.findFirst()
				.get();
			
			List<RefeicaoAlimento> alimentosDaRefeicao = refeicaoEncontrada.getAlimentos();
			return alimentosDaRefeicao.stream()
				.map(alimento -> String.format("%s (%s %s)", alimento.getAlimento().getDescricao(), alimento.getQuantidade(), alimento.getMedida().getDescricaoApresentacao()))
				.collect(Collectors.toList());
			
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
	}
}
