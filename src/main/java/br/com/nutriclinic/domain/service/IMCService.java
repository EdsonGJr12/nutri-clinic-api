package br.com.nutriclinic.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriclinic.domain.model.ImcModel;
import br.com.nutriclinic.domain.repository.ImcRepository;
import br.com.nutriclinic.domain.repository.entity.Imc;

@Service
public class IMCService {
	
	@Autowired
	private ImcRepository imcRepository;
	
	public ImcModel classificarImc(BigDecimal peso, BigDecimal altura) {
		BigDecimal valorImc = calcularImc(peso, altura);
		Imc imc = classificarImc(valorImc);
		
		return new ImcModel(valorImc, "18.5 - 24.9", imc.getClassificacao());
	}
	
	private Imc classificarImc(BigDecimal valorImc) {
		List<Imc> faixas = imcRepository.findAll();
		return faixas.stream()
				.filter(faixa -> {
					boolean igualInicio = valorImc.compareTo(faixa.getFaixaInicio()) == 0;
					boolean maiorInicio = valorImc.compareTo(faixa.getFaixaInicio()) > 0;
					boolean igualFim = valorImc.compareTo(faixa.getFaixaFim()) == 0;
					boolean menorFim = valorImc.compareTo(faixa.getFaixaFim()) < 0;
					
					return (maiorInicio || igualInicio) && (menorFim || igualFim);
				})
				.findFirst().get();
	}

	public BigDecimal calcularImc(BigDecimal peso, BigDecimal altura) {
		return peso.divide(altura.pow(2), 2, RoundingMode.CEILING);
	}
}
