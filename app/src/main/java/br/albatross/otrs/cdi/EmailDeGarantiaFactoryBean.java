package br.albatross.otrs.cdi;

import br.albatross.apis.email.EmailDadosDoEnvioImpl;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaImpl;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class EmailDeGarantiaFactoryBean {

    public EmailDeGarantia getEmailDeGarantia() {

        EmailDeGarantia emailDeGarantia = new EmailDeGarantiaImpl(new EmailDadosDoEnvioImpl());
        return emailDeGarantia;

    }

}
