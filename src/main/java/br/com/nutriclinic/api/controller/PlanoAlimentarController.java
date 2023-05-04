package br.com.nutriclinic.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planos-alimentares")
public class PlanoAlimentarController {
	
	@PutMapping("/{idPlanoAlimentar}")
	public void alterarInformacoesPlanoAlimentar() {
		
	}
	
	@GetMapping("/{idPlanoAlimentar}/refeicoes")
	public void pesquisarRefeicoesDoPlano() {
		
	}
	
	@PostMapping("/{idPlanoAlimentar}/refeicoes")
	public void adicionarRefeicaoNoPlano() {
		
	}
}
