package br.albatross.otrs.domain.services.messages.impl;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.IOException;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import br.albatross.otrs.domain.services.messages.apis.MessageBodyPartsBuilder;
import br.albatross.otrs.domain.services.messages.apis.MessageBuilder;
import br.albatross.otrs.domain.services.messages.apis.MessageFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;

@RequestScoped
public class EmailGarantiaMessageBuilder implements MessageBuilder<EmailDeGarantia> {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Inject
	private MessageFactory<EmailDeGarantia> messageFactory;

	@Inject
	private MessageBodyPartsBuilder<EmailDeGarantia> bodyPartsBuilder;

	@Override
	public Message buildMessage(EmailDeGarantia emailGarantia, Session session) throws IOException, MessagingException {
		Message mensagem = messageFactory.createMessage(session);
		setMessageDeliveryAndRecipientData(mensagem, emailGarantia);

		mensagem.setSubject(emailGarantia.getAssunto());
		mensagem.setContent(new MimeMultipart(bodyPartsBuilder.buildBodyParts(emailGarantia)));

		return mensagem;

	}

	private void setMessageDeliveryAndRecipientData(Message message, EmailDeGarantia emailGarantia) throws AddressException, MessagingException {
		message.setFrom(new InternetAddress(emailGarantia.getDadosDoEnvio().getRemetente()));
		message.setRecipients(TO, InternetAddress.parse(emailGarantia.getDadosDoEnvio().getDestinatario()));

		if ((emailGarantia.getDadosDoEnvio().getCopiaPara() != null) || (!emailGarantia.getDadosDoEnvio().getCopiaPara().isBlank())) {
			message.setRecipients(CC, InternetAddress.parse(emailGarantia.getDadosDoEnvio().getCopiaPara()));
		}
	}

}
