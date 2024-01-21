package br.albatross.otrs.domain.services.messages.apis;

import java.io.IOException;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface MessageBodyPartsBuilder<T> {

	BodyPart[] buildBodyParts(@Valid T t) throws IOException, MessagingException;

}
