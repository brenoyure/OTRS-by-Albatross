package br.albatross.otrs.domain.services.garantia;

import java.io.BufferedWriter;
import java.io.StringWriter;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.annotation.Resource;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.validation.Valid;

/**
*
* EJB Stateless responsável por colocar os <code>Email</code>s de Garantia na fila de mensagens
*
* @author breno.brito
*/
@Stateless
public class SolicitacaoService {

    @Inject @JMSConnectionFactory(value = "java:/jms/RemoteActiveMQConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:jboss/exported/jms/queue/OtrsEmailQueue")
    private Queue queue;

    /**
     * Envia o e-mail da solicitação de garantia em formato JSON para a fila.
     * @param solicitacaoDeGarantia
     */
    @Asynchronous
	public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacaoDeGarantia) {

	    Jsonb jsonb = JsonbBuilder.create();
	    StringWriter stringWriter = new StringWriter();
		BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
	    jsonb.toJson(solicitacaoDeGarantia.getEmailDeGarantia(), bufferedWriter);

        TextMessage textMessage = context.createTextMessage(stringWriter.toString());
        context.createProducer().send(queue, textMessage);

	    try {
			jsonb.close();
			bufferedWriter.close();
			stringWriter.close();
	    } catch (Exception e) { throw new RuntimeException(e); }

	}

}
