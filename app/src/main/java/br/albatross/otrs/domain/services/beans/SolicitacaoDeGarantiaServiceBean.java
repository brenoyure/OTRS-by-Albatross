package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.apis.jms.JMSQueueProducer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@RequestScoped
public class SolicitacaoDeGarantiaServiceBean {

	@Inject
	private FacesContext context;

	@Inject
	private JMSQueueProducer<SolicitacaoDeGarantia> solicitacaoDeGarantiaProducer;

	public void enviarSolicitacao(SolicitacaoDeGarantia solicitacaoDeGarantia) {

		solicitacaoDeGarantiaProducer.sendToJMSQueue(solicitacaoDeGarantia);
		context.addMessage("otrs", new FacesMessage(SEVERITY_INFO, "Solicitação de Garantia Despachada para a Fila de Envios", null));

	}
	
}
