package br.albatross.otrs.domain.services.garantia;

import java.io.IOException;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.validation.Valid;

@Singleton
public class GarantiaService {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Resource(lookup = "java:jboss/mail/OtrsMailSession")
	private Session sessaoEmail;

	private static final String EMAIL_USER      =  "mail.smtp.user";
	private static final String EMAIL_PASSWORD  =  "mail.smtp.pass";

	public void enviarEmail(@Valid EmailGarantia email) {
		try {
			MimeMessage mensagem = new MimeMessage(sessaoEmail);
			mensagem.setFrom(email.getFrom());
			mensagem.setRecipients(Message.RecipientType.TO, email.getTo());
			mensagem.setSubject(email.getSubject());

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(email.getUploadedFile());

			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(assinaturaEmailService.getCorpoDoEmailComAssinatura(email.getNumeroDeSerie(), email.getTicket().getResponsibleUser().getLogin(), email.getTicket().getResponsibleUser().getFirstName(), email.getTicket().getResponsibleUser().getLastName()), "utf-8");

			Multipart multipart = new MimeMultipart(attachPart, bodyPart);
			mensagem.setContent(multipart);

			Transport.send(mensagem, EMAIL_USER, EMAIL_PASSWORD);
			email.getUploadedFile().delete();

		}  catch (IOException e) {
			throw new RuntimeException(e);
			//TODO Implementar a MSG de Erro no Envio.
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
	}
	
}
