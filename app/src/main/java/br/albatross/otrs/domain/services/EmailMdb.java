package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.models.garantia.apis.email.Email;
import br.albatross.otrs.domain.services.garantia.AnexoGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioInputStreamGenerator;
import br.albatross.otrs.domain.services.garantia.ServicoDeEnvioDeEmail;
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
	private ServicoDeEnvioDeEmail servicoDeEmail;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private FormularioInputStreamGenerator formularioFileInputStream;

	@Inject
	private AnexoGenerator anexoGenerator;	

	@Override
	public void onMessage(Message message) {

		try {
			
			servicoDeEmail.enviar(message.getBody(Email.class));

		} catch (JMSException e) { throw new RuntimeException(e); }

	}

}
