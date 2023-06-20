package br.albatross.otrs.domain.services.garantia;

import java.io.IOException;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Singleton
public class GarantiaService {

	@Resource(lookup = "java:jboss/mail/OtrsMailSession")
	private Session sessaoEmail;

	private static final String EMAIL_USER     =  "mail.smtp.user";
	private static final String EMAIL_PASSWORD =  "mail.smtp.pass";
	
	public void enviarEmail(EmailGarantia email) {
		try {
			MimeMessage mensagem = new MimeMessage(sessaoEmail);
			mensagem.setFrom(email.getFrom());
			mensagem.setRecipients(Message.RecipientType.TO, email.getTo());
			mensagem.setSubject(email.getSubject());

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(email.getUploadedFile());

			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(getEmailComAssinatura(email.getBody(), email.getNumeroDeSerie()), "utf-8");

			Multipart multipart = new MimeMultipart(attachPart, bodyPart);
			mensagem.setContent(multipart);

			Transport.send(mensagem, EMAIL_USER, EMAIL_PASSWORD);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
			//TODO Implementar a MSG de Erro no Envio.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
	}
	
	private String getEmailComAssinatura(String body, String numeroDeSerie) {
		return String.format("""
Prezados,
Segue o formulário preenchido para abertura de chamado para o equipamento: %s.
%s
				
Atenciosamente,
--
Breno Brito 
Técnico de Suporte Nível 1 
(55) 4433-2142
email@albatross.com
				
				""", numeroDeSerie, body);
	}
	
}
