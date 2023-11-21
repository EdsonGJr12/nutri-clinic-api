package br.com.nutriclinic.domain.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import br.com.nutriclinic.domain.model.Notificacao;
import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.ExpoPushTicket;
import io.github.jav.exposerversdk.PushClient;
import io.github.jav.exposerversdk.enums.Priority;
import io.github.jav.exposerversdk.enums.Status;

@Service
public class PushNotificationService {
	public void notificar(Notificacao notificacao, String pushToken) {
		try {
			if (!PushClient.isExponentPushToken(pushToken)) {
				throw new RuntimeException("Token inválido");
			}

			ExpoPushMessage expoPushMessage = new ExpoPushMessage();
			expoPushMessage.getTo().add(pushToken);
			expoPushMessage.setTitle(notificacao.getTitulo());
			expoPushMessage.setBody(notificacao.getCorpo());
			expoPushMessage.setPriority(Priority.ERROR);

			PushClient client = new PushClient();

			ExpoPushTicket ticket = client.sendPushNotificationsAsync(Arrays.asList(expoPushMessage)).get().get(0);

			if (ticket.getStatus().equals(Status.ERROR)) {
				throw new RuntimeException(ticket.getMessage());
			}  
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
