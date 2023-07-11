package br.albatross.otrs.domain.services.messages.impl;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.messages.apis.MessageFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

@RequestScoped
public class EmailGarantiaMimeMessageFactory implements MessageFactory<EmailDeGarantia> {

	@Override
	public Message createMessage(Session session) {
		return new MimeMessage(session);
	}

}
