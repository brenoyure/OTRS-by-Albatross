package br.albatross.otrs.domain.services.messages;

import java.io.IOException;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;

public interface MessageBodyPartsBuilder<T> {

	BodyPart[] buildBodyParts(T t) throws IOException, MessagingException;

}
