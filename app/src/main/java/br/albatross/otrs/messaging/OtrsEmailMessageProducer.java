package br.albatross.otrs.messaging;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.validation.Valid;

@Stateless
public class OtrsEmailMessageProducer {

    @Inject @JMSConnectionFactory(value = "java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/OtrsEmailQueue")
    private Queue queue;

    public void enviarEmailParaAJmsQueue(@Valid EmailDeGarantia email) {
        context.createProducer().send(queue, email);
    }

}
