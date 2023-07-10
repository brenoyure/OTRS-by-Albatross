package br.albatross.otrs.domain.services.messages;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.IOException;

import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;

@RequestScoped
public class EmailGarantiaMessageBuilder implements MessageBuilder<EmailGarantia> {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Inject
	private MessageFactory<EmailGarantia> messageFactory;

	@Inject
	private MessageBodyPartsBuilder<EmailGarantia> bodyPartsBuilder;

	@Override
	public Message buildMessage(EmailGarantia emailGarantia, Session session) throws IOException, MessagingException {
		Message mensagem = messageFactory.createMessage(session);
		setMessageDeliveryAndRecipientData(mensagem, emailGarantia);

		mensagem.setSubject(emailGarantia.getSubject());
		mensagem.setContent(new MimeMultipart(bodyPartsBuilder.buildBodyParts(emailGarantia)));

		return mensagem;

	}

	private void setMessageDeliveryAndRecipientData(Message message, EmailGarantia emailGarantia) throws AddressException, MessagingException {
		message.setFrom(new InternetAddress(emailGarantia.getFrom()));
		message.setRecipients(TO, InternetAddress.parse(emailGarantia.getTo()));

		if ((emailGarantia.getCc() != null) || (!emailGarantia.getCc().isBlank())) {
			message.setRecipients(CC, InternetAddress.parse(emailGarantia.getCc()));
		}
	}

}
