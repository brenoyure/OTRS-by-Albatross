package br.albatross.otrs.cdi;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaDadosDoEnvioImpl;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaImpl;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class SolicitacaoDeGarantiaFactoryBean {

    public SolicitacaoDeGarantia getSolicitacaoDeGarantia() {

        SolicitacaoDeGarantia solicitacao = new SolicitacaoDeGarantiaImpl();
        EmailDeGarantia emailDeGarantia = new EmailDeGarantiaImpl();
        emailDeGarantia.setDadosDoEnvio(new EmailDeGarantiaDadosDoEnvioImpl());
        solicitacao.setEmailDeGarantia(emailDeGarantia);
        solicitacao.getEmailDeGarantia().setSolicitacaoGarantia(solicitacao);

        return solicitacao;

    }

}
