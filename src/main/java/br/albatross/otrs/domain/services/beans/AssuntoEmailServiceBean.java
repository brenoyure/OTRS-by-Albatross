package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.Optional;

import br.albatross.otrs.domain.services.garantia.AssuntoEmailService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
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
	private AssuntoEmailService assuntoEmailService;

	public void setAssuntoDoEmail(EmailGarantia emailGarantia) {
		if (emailGarantia.getTicket() == null) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Ticket não selecionado", "Para utilizar melhor a função de gerar os textos, por favor selecione um Ticket válido e tente novamente."));
			return;
		}

		assuntoEmailService.setEmailSubject(emailGarantia);
	}

	public Optional<String> getAssuntoDoEmail(EmailGarantia emailGarantia) {
		return assuntoEmailService.getEmailSubject(emailGarantia);
	}

}
