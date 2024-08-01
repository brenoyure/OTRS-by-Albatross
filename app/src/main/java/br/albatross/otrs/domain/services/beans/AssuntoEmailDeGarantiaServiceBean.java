package br.albatross.otrs.domain.services.beans;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AssuntoEmailDeGarantiaServiceBean {

    @Inject
    private AssuntoEmailService assuntoEmailService;
    
	public void setAssuntoDoEmail(EmailDeGarantia emailGarantia) {

	    String assuntoDoEmail = assuntoEmailService.getAssuntoDoEmailBaseadoNoServicoDoChamado(emailGarantia);
	    emailGarantia.setAssunto(assuntoDoEmail);

	}

}
