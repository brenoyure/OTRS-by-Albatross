package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import br.albatross.otrs.domain.services.EmailGarantiaService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
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

	public void enviarSolicitacaoDeGarantia(EmailGarantia emailGarantia) {
		emailGarantiaService.enviarSolicitacaoDeGarantiaParaFilaDeEnvios(emailGarantia);
		context.addMessage("otrs", new FacesMessage(SEVERITY_INFO, "E-mail despachado para fila de envios", "E-mail despachado para a fila de envios e logo será enviado."));

	}
	
}
