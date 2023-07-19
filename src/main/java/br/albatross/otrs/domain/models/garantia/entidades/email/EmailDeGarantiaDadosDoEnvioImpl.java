package br.albatross.otrs.domain.models.garantia.entidades.email;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.email.DadosDoEnvio;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailDeGarantiaDadosDoEnvioImpl implements DadosDoEnvio, Serializable {

	private static final long serialVersionUID = 1L;

	private String remetente;

	private String destinatario;

	private String copiaPara;

	private EmailDeGarantia emailDeGarantia;

}
