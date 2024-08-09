package br.albatross.otrs.messaging;

import br.albatross.otrs.domain.models.garantia.apis.email.Email;
import br.albatross.otrs.domain.services.garantia.ServicoDeEnvioDeEmail;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

/**
 * 
 * EJB MessageDriven responsável por consumir a fila de mensageria.
 * 
 * chamando o ServiçoDeEnvioDeEmail e despachando os emails.
 * 
 * @author breno.brito
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType",   propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jms/queue/OtrsEmailQueue") })
public class OtrsEmailQueueConsumer implements MessageListener {

	@Inject
	private ServicoDeEnvioDeEmail servicoDeEmail;

	@Override
	public void onMessage(Message message) {

		try {
			servicoDeEmail.enviar(message.getBody(Email.class));

		} catch (JMSException e) { throw new RuntimeException(e); }

	}

}
