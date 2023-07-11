package br.albatross.otrs.domain.services.messages.apis;

import java.io.IOException;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MessageBuilder<T> {

	Message buildMessage(@Valid T t, @NotNull Session session) throws IOException, AddressException, MessagingException;

}
