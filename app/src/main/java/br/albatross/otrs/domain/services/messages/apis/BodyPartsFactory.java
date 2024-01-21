package br.albatross.otrs.domain.services.messages.apis;

import java.io.IOException;

import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface BodyPartsFactory<T> {

	BodyPart[] getBodyParts(@NotNull T[] anexos) throws IOException, MessagingException;

	BodyPart[] getBodyParts(@NotBlank String emailTextBody, @NotNull T[] anexos) throws IOException, MessagingException;
}
