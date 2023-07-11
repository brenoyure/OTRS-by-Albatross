package br.albatross.otrs.domain.services.messages.impl;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.messages.apis.BodyPartsFactory;
import br.albatross.otrs.domain.services.messages.apis.MessageBodyPartsBuilder;
import jakarta.inject.Inject;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;

public class EmailGarantiaMessageBodyPartsBuilder implements MessageBodyPartsBuilder<EmailDeGarantia> {

	@Inject
	private BodyPartsFactory<File> factory;

	public BodyPart[] buildBodyParts(EmailDeGarantia emailGarantia) throws IOException, MessagingException {
		return factory.getBodyParts(emailGarantia.getCorpoDaMensagem(), emailGarantia.getAnexos());
	}

}
