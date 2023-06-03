package br.com.nutriclinic.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriclinic.api.model.PlanoAlimentarModel;
import br.com.nutriclinic.api.model.RefeicaoDiaModel;
import br.com.nutriclinic.domain.exception.NegocioException;
import br.com.nutriclinic.domain.repository.PlanoAlimentarRepository;
import br.com.nutriclinic.domain.repository.entity.PlanoAlimentar;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin
public class PacienteController {
	
	@Autowired
	private PlanoAlimentarRepository planoAlimentarRepository;
	
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
	public List<Integer> buscarDiasNaSemanaComPlano(@PathVariable Long idPaciente) {
		List<Integer> diasSemana = new ArrayList<>();
		
		Optional<PlanoAlimentar> planoAlimentarOp = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentarOp.isPresent()) {
			PlanoAlimentar planoAlimentar = planoAlimentarOp.get();
			if (planoAlimentar.getSegunda()) {
				diasSemana.add(1);
			}
			if (planoAlimentar.getTerca()) {
				diasSemana.add(2);
			}
			if (planoAlimentar.getQuarta()) {
				diasSemana.add(3);
			}
			if (planoAlimentar.getQuinta()) {
				diasSemana.add(4);
			}
			if (planoAlimentar.getSexta()) {
				diasSemana.add(5);
			}
			if (planoAlimentar.getSabado()) {
				diasSemana.add(6);
			}
			if (planoAlimentar.getDomingo()) {
				diasSemana.add(7);
			}
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
		
		return diasSemana;
	}
	
	@GetMapping("/{idPaciente}/ultimo-plano/dias-semana/{diaSemana}")
	public RefeicaoDiaModel buscarRefeicoes(@PathVariable Long idPaciente, @PathVariable Integer diaSemana) {
		RefeicaoDiaModel refeicaoDiaModel = new RefeicaoDiaModel();
		
		Optional<PlanoAlimentar> planoAlimentarOp = planoAlimentarRepository.findFirstByPacienteIdOrderByDataHoraInclusaoDesc(idPaciente);
		if (planoAlimentarOp.isPresent()) {
			PlanoAlimentar planoAlimentar = planoAlimentarOp.get();
			
			refeicaoDiaModel.setRefeicoes(planoAlimentar.getRefeicoes().stream().map(refeicao -> {
				return refeicao.getDescricao() + "---------------" + refeicao.getHorario();
			}).toList());
			
			if (diaSemana.equals(1)) {
				refeicaoDiaModel.setDiaSemana("Segunda");
			}
			if (planoAlimentar.getTerca()) {
				refeicaoDiaModel.setDiaSemana("Terça");
			}
			if (planoAlimentar.getQuarta()) {
				refeicaoDiaModel.setDiaSemana("Quarta");
			}
			if (planoAlimentar.getQuinta()) {
				refeicaoDiaModel.setDiaSemana("Quinta");
			}
			if (planoAlimentar.getSexta()) {
				refeicaoDiaModel.setDiaSemana("Sexta");
			}
			if (planoAlimentar.getSabado()) {
				refeicaoDiaModel.setDiaSemana("Sábado");
			}
			if (planoAlimentar.getDomingo()) {
				refeicaoDiaModel.setDiaSemana("Domingo");
			}
		} else {
			throw new NegocioException("Plano alimentar não encontrado");
		}
		
		return refeicaoDiaModel;
	}
}
