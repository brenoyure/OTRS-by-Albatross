package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
public class AssuntoEmailServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	@Inject
	private AssuntoEmailService assuntoService;

	public void setAssuntoDoEmail(EmailDeGarantia emailGarantia) {
		assuntoService
		              .getAssuntoDoEmailBaseadoNoServicoDoChamado(emailGarantia)
		              .ifPresentOrElse(assunto -> emailGarantia.setAssunto(assunto), 
		            		  () -> exibirMensagemDeTicketNaoSelecionado());
	}

	public Optional<String> getAssuntoDoEmail(EmailDeGarantia emailGarantia) {
		return assuntoService.getEmailSubject(emailGarantia);
	}

	private void exibirMensagemDeTicketNaoSelecionado() {
		context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Ticket não selecionado",
				"Para utilizar melhor a função de gerar os textos, por favor selecione um Ticket válido e tente novamente."));
	}

}
