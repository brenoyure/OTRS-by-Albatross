package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.garantia.SolicitacaoDeGarantiaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@RequestScoped
public class SolicitacaoDeGarantiaServiceBean {

	@Inject
	private SolicitacaoDeGarantiaService solicitacaoService;

	@Inject
	private FacesContext context;

	public void solicitarGarantia(SolicitacaoDeGarantia solicitacaoDeGarantia) {
	    solicitacaoService.solicitarGarantia(solicitacaoDeGarantia);
		context.addMessage("otrs", 
		        new FacesMessage(SEVERITY_INFO, 
		                "Solicitação despachada para fila de envios", 
		                "Você pode conferir o status da Solicitação através do Sistema de Chamados, ou na Caixa de Entrada dos e-mails que receberam cópia. Lembrando que Sistemas de Chamados podem levar alguns minutos para registrarem a Solicitação."));

	}
	
}
