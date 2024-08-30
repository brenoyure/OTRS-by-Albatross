package br.albatross.otrs.jms.consumer;

import org.jboss.ejb3.annotation.ResourceAdapter;

import br.albatross.apis.email.Email;
import br.albatross.apis.email.ServicoDeEnvioDeEmail;
import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.mail.Session;

/**
*
* EJB MessageDriven responsável por consumir a fila de mensageria.
*
* chamando o ServiçoDeEnvioDeEmail e despachando os emails.
*
* @author breno.brito
*/
@ResourceAdapter("remote-activemq-connection-factory")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",  propertyValue = "jms.queue.OtrsEmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",    propertyValue = "jakarta.jms.Queue")})
public class OtrsEmailQueueConsumer implements MessageListener {

    @Inject
    private ServicoDeEnvioDeEmail servicoDeEmail;

    @Resource(lookup = "java:jboss/mail/OtrsMailSession")
    private Session mailSession;

    @Override
    public void onMessage(Message message) {
        try {

            servicoDeEmail.enviar(message.getBody(Email.class), mailSession);

        } catch (JMSException e) { throw new RuntimeException(e); }

    }

}
