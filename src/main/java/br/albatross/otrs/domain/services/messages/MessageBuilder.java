package br.albatross.otrs.domain.services.messages;

import java.io.IOException;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;

public interface MessageBuilder<T> {

	Message buildMessage(T t, Session session) throws IOException, AddressException, MessagingException;

}
