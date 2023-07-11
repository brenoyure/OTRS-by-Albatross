package br.albatross.otrs.domain.models.garantia.apis.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface DadosDoEnvio {

	@NotBlank @Email
	String getRemetente();

	@NotBlank
	String getDestinatario();

	String getCopiaPara();

	void setRemetente(String remetente);

	void setDestinatario(String destinatario);

	void setCopiaPara(String copiaPara);

}
