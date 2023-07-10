package br.albatross.otrs.domain.services.garantia;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.services.BodyPartsFactory;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.validation.Valid;

@Stateless
public class GarantiaService {

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	@Inject
	private BodyPartsFactory<File> bodyPartsFactory;

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

			BodyPart[] bodyParts = bodyPartsFactory.getBodyParts(assinaturaEmailService.getCorpoDoEmailComAssinatura(email.getNumeroDeSerie(), 
					                                                                		                         email.getTicket().getResponsibleUser().getLogin(), 
					                                                                		                         email.getTicket().getResponsibleUser().getFirstName(), 
					                                                                		                         email.getTicket().getResponsibleUser().getLastName()), 
					                                                                                                 email.getUploadedFiles());

			Multipart multipart = new MimeMultipart(bodyParts);
			mensagem.setContent(multipart);

			Transport.send(mensagem);

			deleteTempFiles(email.getUploadedFiles());

		}
           catch (IOException e)        { throw new RuntimeException(e); }  
           catch (MessagingException e) { throw new RuntimeException(e); }
	}

	private void deleteTempFiles(File[] filesToDelete) {
		for (File uploadedFile : filesToDelete) {
			if (uploadedFile.exists()) {
				if (uploadedFile.canWrite()) {
					uploadedFile.delete();
				}
			}
		}
	}

}
