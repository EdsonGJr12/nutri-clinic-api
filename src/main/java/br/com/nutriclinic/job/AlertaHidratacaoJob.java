package br.com.nutriclinic.job;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.nutriclinic.domain.model.Notificacao;
import br.com.nutriclinic.domain.repository.AlertaHidratacaoRepository;
import br.com.nutriclinic.domain.repository.HistoricoNotificacoesRepository;
import br.com.nutriclinic.domain.repository.entity.AlertaHidratacao;
import br.com.nutriclinic.domain.repository.entity.HistoricoNotificacoes;
import br.com.nutriclinic.domain.service.PushNotificationService;

@EnableScheduling
@Component
public class AlertaHidratacaoJob {
	
	@Autowired
	private AlertaHidratacaoRepository alertaHidratacaoRepository;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@Autowired
	private HistoricoNotificacoesRepository historicoNotificacoesRepository;
	
	@Scheduled(cron = "0 * * * * *")
	@Transactional
	public void executar() {
		LocalTime agora = LocalTime.now()
				.withSecond(0)
				.withNano(0);
		
		List<AlertaHidratacao> alertas = alertaHidratacaoRepository.findByHorario(agora);
		
		alertas.forEach(alerta -> {
			String pushToken = alerta.getUsuario().getPushToken();
			if (pushToken != null) {
				String titulo = "Teste título";
				String corpo = "Teste corpo";
				
				Notificacao notificacao = new Notificacao(titulo, corpo);
				pushNotificationService.notificar(notificacao, pushToken);
				
				HistoricoNotificacoes historicoNotificacao = new HistoricoNotificacoes();
				historicoNotificacao.setTitulo(titulo);
				historicoNotificacao.setCorpo(corpo);
				historicoNotificacoesRepository.save(historicoNotificacao);
			}
		});
	}
}
