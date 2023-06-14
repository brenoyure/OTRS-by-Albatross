package br.albatross.otrs.domain.services.garantia;

import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

@Singleton
public class GarantiaService {

	@Resource(lookup = "java:jboss/mail/OtrsMailSession")
	private Session sessaoEmail;

	private static final String EMAIL_FROM     =  "smtp.gmail.com";
	private static final String EMAIL_USER     =  "mail.smtp.user";
	private static final String EMAIL_PASSWORD =  "mail.smtp.pass";
	
	public void enviarEmail(EmailGarantia email) {
		try {
			MimeMessage mensagem = new MimeMessage(sessaoEmail);
			mensagem.setFrom(EMAIL_FROM);
			mensagem.setRecipients(Message.RecipientType.TO, email.getTo());
			mensagem.setSubject(email.getSubject());
			mensagem.setText(getEmailComAssinatura(email.getBody(), email.getProblema().getNumeroDeSerie()));
			Transport.send(mensagem, EMAIL_USER, EMAIL_PASSWORD);
		} catch (MessagingException e) {
			e.printStackTrace();
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
