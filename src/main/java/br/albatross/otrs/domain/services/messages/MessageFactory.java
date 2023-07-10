package br.albatross.otrs.domain.services.messages;

import jakarta.mail.Message;
import jakarta.mail.Session;

public interface MessageFactory<T> {

	Message createMessage(Session session);
	
}
