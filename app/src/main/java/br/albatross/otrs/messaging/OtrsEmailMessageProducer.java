package br.albatross.otrs.messaging;

import br.albatross.apis.email.Email;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.validation.Valid;

/**
 *
 * EJB Stateless responsável por colocar os <code>Email</code>s na fila de mensageria.
 *
 * @author breno.brito
 */
@Stateless
public class OtrsEmailMessageProducer {

    @Inject @JMSConnectionFactory(value = "java:/jms/RemoteActiveMQConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:jboss/exported/jms/queue/OtrsEmailQueue")
    private Queue queue;

    public void enviarEmailParaAJmsQueue(@Valid Email email) {
        context.createProducer().send(queue, email);
    }

}
