package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.garantia.GarantiaService;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",   propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jms/queue/OtrsEmailQueue") })
public class EmailMdb implements MessageListener {

	@Inject
	private GarantiaService<EmailDeGarantia> garantiaService;

	@Override
	public void onMessage(Message message) {

		try {
			garantiaService.enviarEmail(message.getBody(EmailDeGarantia.class));

		} catch (JMSException e) { throw new RuntimeException(e); }

	}

}
