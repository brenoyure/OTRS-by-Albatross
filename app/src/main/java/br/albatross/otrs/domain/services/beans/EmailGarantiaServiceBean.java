package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.EmailGarantiaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@RequestScoped
public class EmailGarantiaServiceBean {

	@Inject
	private EmailGarantiaService emailGarantiaService;

	@Inject
	private FacesContext context;

	public void enviarSolicitacaoDeGarantia(EmailDeGarantia email) {
		emailGarantiaService.enviarEmailDeGarantiaParaFilaDeEnvios(email);
		context.addMessage("otrs", 
		        new FacesMessage(SEVERITY_INFO, 
		                "Solicitação despachada para fila de envios", 
		                "Você pode conferir o status da Solicitação através do Sistema de Chamados, ou na Caixa de Entrada dos e-mails que receberam cópia. Lembrando que Sistemas de Chamados podem levar alguns minutos para registrarem a Solicitação."));

	}
	
}
