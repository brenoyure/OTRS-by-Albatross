package br.albatross.otrs.domain.services.messages.apis;

import java.io.IOException;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * <p>Responsável por criar um <code>Message</code> a partir de um <code>Tipo</code> e um Session fornecido.</p>
 * <p>Esta interface pode ser utilizada na camada de negócio da aplicação para criar um Message a partir de um <code>Tipo</code> fornecido</p>
 * @param <T> o tipo do e-mail
 */
public interface MessageBuilder<T> {

	/**
	 * Cria um <code>Message</code> a partir do <code><strong>Tipo</strong></code> e <code><strong>Session</strong></code> fornecidos.
	 * 
	 * @param tipo que será utilizado para criação do <code>Message</code>.
	 * @param session que será utilizado na criação do Message.
	 * @return Message
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	Message buildMessage(@Valid T tipo, @NotNull Session session) throws IOException, AddressException, MessagingException;

}
