package br.albatross.otrs.domain.services;

import java.io.IOException;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;

public interface BodyPartsFactory<T> {

	BodyPart[] getBodyParts(T[] anexos) throws IOException, MessagingException;

	BodyPart[] getBodyParts(String emailTextBody, T[] anexos) throws IOException, MessagingException;
}
