package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.services.garantia.EmailGarantia;

import jakarta.annotation.Resource;

import jakarta.ejb.Singleton;

import jakarta.inject.Inject;

import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.validation.Valid;

@Singleton
public class OtrsJMSQueueEmailProducer {

	@Inject @JMSConnectionFactory(value = "java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/OtrsEmailQueue")
	private Queue queue;

	public void enviarEmailParaAJmsQueue(@Valid EmailGarantia email) {
		context.createProducer().send(queue, email);
	}

}
