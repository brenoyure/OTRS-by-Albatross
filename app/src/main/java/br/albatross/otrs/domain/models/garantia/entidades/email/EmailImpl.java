package br.albatross.otrs.domain.models.garantia.entidades.email;

import java.io.File;

import br.albatross.otrs.domain.models.garantia.apis.email.DadosDoEnvio;
import br.albatross.otrs.domain.models.garantia.apis.email.Email;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailImpl implements Email {

    private static final long serialVersionUID = 1L;

    private String assunto;

    private String corpoDaMensagem;

    private File[] anexos;

    private DadosDoEnvio dadosDoEnvio;

    public EmailImpl() {

    }

    public EmailImpl(DadosDoEnvio dadosDoEnvio) {

        this.dadosDoEnvio = dadosDoEnvio;

    }

}
