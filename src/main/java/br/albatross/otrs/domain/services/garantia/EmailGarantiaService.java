package br.albatross.otrs.domain.services.garantia;

import java.io.File;
import java.io.IOException;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.services.messages.apis.MessageBuilder;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;

@Stateless
public class EmailGarantiaService implements GarantiaService<EmailDeGarantia> {

	@Inject
	private MessageBuilder<EmailDeGarantia> builder;

	@Resource(lookup = "java:jboss/mail/OtrsMailSession")
	private Session sessaoEmail;

	public void enviarEmail(@Valid EmailDeGarantia email) {
		try {
			Message mensagem = builder.buildMessage(email, sessaoEmail);
			Transport.send(mensagem);
			deleteTempFiles(email.getAnexos());
		}
           catch (IOException e)        { throw new RuntimeException(e); }
		   catch (AddressException e)   { throw new RuntimeException(e); }
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
