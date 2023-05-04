package br.com.nutriclinic.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refeicoes")
public class RefeicaoController {
	
	@PostMapping("/{idRefeicao}/alimento")
	public void adicionarAlimentoNaRefeicao() {
		
	}
	
	@DeleteMapping("/{idRefeicao}/alimento")
	public void removerAlimentoDaRefeicao() {
		
	}
}
