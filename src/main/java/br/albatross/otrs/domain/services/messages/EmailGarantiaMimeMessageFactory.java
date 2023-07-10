package br.albatross.otrs.domain.services.messages;

import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

@RequestScoped
public class EmailGarantiaMimeMessageFactory implements MessageFactory<EmailGarantia> {

	@Override
	public Message createMessage(Session session) {
		return new MimeMessage(session);
	}

}
