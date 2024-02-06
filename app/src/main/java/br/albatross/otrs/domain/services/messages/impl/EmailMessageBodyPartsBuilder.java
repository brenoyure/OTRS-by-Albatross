package br.albatross.otrs.domain.services.messages.impl;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.models.garantia.apis.email.Email;
import br.albatross.otrs.domain.services.messages.apis.BodyPartsFactory;
import br.albatross.otrs.domain.services.messages.apis.MessageBodyPartsBuilder;
import jakarta.inject.Inject;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;

public class EmailMessageBodyPartsBuilder implements MessageBodyPartsBuilder<Email> {

	@Inject
	private BodyPartsFactory<File> factory;

	public BodyPart[] buildBodyParts(Email email) throws IOException, MessagingException {
		return factory.getBodyParts(email.getCorpoDaMensagem(), email.getAnexos());
	}

}
