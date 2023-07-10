package br.albatross.otrs.domain.services.messages;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.inject.Inject;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;

public class EmailGarantiaMessageBodyPartsBuilder implements MessageBodyPartsBuilder<EmailGarantia> {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Inject
	private BodyPartsFactory<File> factory;

	public BodyPart[] buildBodyParts(EmailGarantia emailGarantia) throws IOException, MessagingException {
		BodyPart[] bodyParts = factory.getBodyParts(assinaturaEmailService.getCorpoDoEmailComAssinatura(emailGarantia.getNumeroDeSerie(), 
                                                                                                        emailGarantia.getTicket().getResponsibleUser().getLogin(), 
                                                                                                        emailGarantia.getTicket().getResponsibleUser().getFirstName(), 
                                                                                                        emailGarantia.getTicket().getResponsibleUser().getLastName()), 
                                                                                                        emailGarantia.getUploadedFiles());
		return bodyParts;
	}

}
