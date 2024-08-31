package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.messaging.OtrsEmailMessageProducer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class SolicitacaoDeGarantiaService {

	@Inject
	private FacesContext context;

	@Inject
	private OtrsEmailMessageProducer messageProducer;

	public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacaoDeGarantia) {
	    messageProducer.enviarEmailParaAJmsQueue(solicitacaoDeGarantia.getEmailDeGarantia());
		context.addMessage(null, 
		        new FacesMessage(SEVERITY_INFO, 
		                "Solicitação despachada para fila de envios", 
		                "Você pode conferir o status da Solicitação através do Sistema de Chamados, ou na Caixa de Entrada dos e-mails que receberam cópia. Lembrando que Sistemas de Chamados podem levar alguns minutos para registrarem a Solicitação."));

	}
	
}
