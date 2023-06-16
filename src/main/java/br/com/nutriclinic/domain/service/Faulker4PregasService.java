package br.com.nutriclinic.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriclinic.domain.form.Faulker4PregasForm;
import br.com.nutriclinic.domain.model.Faulker4PregasModel;
import br.com.nutriclinic.domain.repository.Faulker4PregasRepository;
import br.com.nutriclinic.domain.repository.entity.Faulker4Pregas;

@Service
public class Faulker4PregasService {
	
	@Autowired
	private Faulker4PregasRepository faulker4PregasRepository;
	
	public Faulker4PregasModel classificar(Faulker4PregasForm form) {
		
		BigDecimal somaDasDobras = form.getTriceps().add(form.getSubescapular()).add(form.getSuprailiaca()).add(form.getAbdominal());
		BigDecimal percentualGordura = (somaDasDobras.multiply(BigDecimal.valueOf(0.153)))
				.add(BigDecimal.valueOf(5.783)).setScale(2, RoundingMode.CEILING);
		
		List<Faulker4Pregas> ocorrencias = faulker4PregasRepository.findBySexo(form.getSexo());
		Faulker4Pregas faulker4Pregas = ocorrencias.stream()
			.filter(ocorrencia -> {
				boolean isEncontrado = false;
				
				double faixaInicio = ocorrencia.getFaixaInicio().doubleValue();
				double faixaFim = ocorrencia.getFaixaFim().doubleValue();
				double percentualGorduraCalculado = percentualGordura.doubleValue();
				
				isEncontrado = form.getIdade() >= ocorrencia.getIdadeInicio() && form.getIdade() <= ocorrencia.getIdadeFim();
				isEncontrado = isEncontrado && percentualGorduraCalculado >= faixaInicio && percentualGorduraCalculado <= faixaFim;
				
				return isEncontrado;
			})
			.findFirst()
			.get();
		
		return new Faulker4PregasModel(percentualGordura, null, faulker4Pregas.getClassificacao());
	}
}
