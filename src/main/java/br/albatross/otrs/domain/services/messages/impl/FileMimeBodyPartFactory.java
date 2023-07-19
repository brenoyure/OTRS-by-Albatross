package br.albatross.otrs.domain.services.messages.impl;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.services.messages.apis.BodyPartsFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;

@RequestScoped
public class FileMimeBodyPartFactory implements BodyPartsFactory<File> {

	@Override
	public BodyPart[] getBodyParts(File[] anexos) throws IOException, MessagingException {

		MimeBodyPart[] parts = new MimeBodyPart[(anexos.length)];

		for (int i = 0; i < anexos.length; i++) {
			var anexoBodypart = new MimeBodyPart();
			anexoBodypart.attachFile(anexos[i]);
			parts[i] = anexoBodypart;
		}

		return parts;

	}

	@Override
	public BodyPart[] getBodyParts(String emailTextBody, File[] anexos) throws IOException, MessagingException {

		MimeBodyPart[] parts = new MimeBodyPart[(1 + anexos.length)];

		for (int i = 0; i < anexos.length; i++) {
			var anexoBodypart = new MimeBodyPart();
			anexoBodypart.attachFile(anexos[i]);
			parts[i] = anexoBodypart;
		}

		var textBodyPart = new MimeBodyPart();
		textBodyPart.setText(emailTextBody);
		parts[parts.length - 1] = textBodyPart;

		return parts;

	}

}
