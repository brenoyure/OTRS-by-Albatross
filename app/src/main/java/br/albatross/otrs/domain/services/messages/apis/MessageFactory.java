package br.albatross.otrs.domain.services.messages.apis;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.validation.constraints.NotNull;

public interface MessageFactory<T> {

	Message createMessage(@NotNull Session session);
	
}
