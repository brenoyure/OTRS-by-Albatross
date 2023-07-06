package br.albatross.otrs.domain.services.garantia;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.File;
import java.io.IOException;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.validation.Valid;

@Stateless
public class GarantiaService {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Resource(lookup = "java:jboss/mail/OtrsMailSession")
	private Session sessaoEmail;

	public void enviarEmail(@Valid EmailGarantia email) {
		try {
			MimeMessage mensagem = new MimeMessage(sessaoEmail);
			mensagem.setFrom(email.getFrom());
			mensagem.setRecipients(TO, InternetAddress.parse(email.getTo()));

			if ((email.getCc() != null) || (!email.getCc().isBlank())) {
				mensagem.setRecipients(CC, InternetAddress.parse(email.getCc()));
			}

			mensagem.setSubject(email.getSubject());

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(assinaturaEmailService.getCorpoDoEmailComAssinatura(email.getNumeroDeSerie(), email.getTicket().getResponsibleUser().getLogin(), email.getTicket().getResponsibleUser().getFirstName(), email.getTicket().getResponsibleUser().getLastName()), "utf-8");

			/*
			 * Begin of Attach part
			 * 
			 */

			File[] anexos = email.getUploadedFiles();

			MimeBodyPart formularioattachPart = new MimeBodyPart();
			formularioattachPart.attachFile(anexos[0]);

			MimeBodyPart anexoExtraAttachPart = null;
			if (anexos.length > 1) {
				anexoExtraAttachPart = new MimeBodyPart();
				anexoExtraAttachPart.attachFile(anexos[1]);
			}

			MimeBodyPart[] bodyParts = new MimeBodyPart[(1 + anexos.length)];	
			bodyParts[0] = textBodyPart;
			bodyParts[1] = formularioattachPart;
			
			if (anexoExtraAttachPart != null) {
				bodyParts[2] = anexoExtraAttachPart;
			}
			
			/*
			 * End of attach part
			 */

			Multipart multipart = new MimeMultipart(bodyParts);
			mensagem.setContent(multipart);

			Transport.send(mensagem);

			for (File uploadedFile : email.getUploadedFiles()) {
				if (uploadedFile.exists()) {
					if (uploadedFile.canWrite()) {
						uploadedFile.delete();
					}
				}
			}

		}
           catch (IOException e)        { throw new RuntimeException(e); }  
           catch (MessagingException e) { throw new RuntimeException(e); }
	}

}
