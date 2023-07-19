package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.email.Email;
import jakarta.validation.Valid;

/**
 * Serviço Responsável pelo envio de e-mails na camada de negócios da aplicação.
 */
public interface ServicoDeEnvioDeEmail {

	/**
	 * Envia o e-mail
	 * @param email que será enviado.
	 */
	void enviar(@Valid Email email);

}
