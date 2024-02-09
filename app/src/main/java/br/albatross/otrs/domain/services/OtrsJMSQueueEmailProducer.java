package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.apis.jms.JMSQueueProducer;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.validation.Valid;

@Stateless
public class OtrsJMSQueueEmailProducer implements JMSQueueProducer<SolicitacaoDeGarantia> {

	@Inject @JMSConnectionFactory(value = "java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/OtrsEmailQueue")
	private Queue queue;

	@Override
	public void sendToJMSQueue(@Valid SolicitacaoDeGarantia solicitacaoDeGarantia) {
		context.createProducer().send(queue, solicitacaoDeGarantia);
		
	}

}
