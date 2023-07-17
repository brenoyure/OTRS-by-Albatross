package br.albatross.otrs.domain.services.beans;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AssinaturaEmailServiceBean {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	public void setCorpoDaMensagemComAssinatura(EmailDeGarantia emailDeGarantia) {

		emailDeGarantia.setCorpoDaMensagem(assinaturaEmailService.getCorpoDoEmailComAssinatura(emailDeGarantia.getSolicitacaoGarantia().getNumeroDeSerie()));

	}

}
