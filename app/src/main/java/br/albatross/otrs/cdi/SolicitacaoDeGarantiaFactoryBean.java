package br.albatross.otrs.cdi;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SolicitacaoDeGarantiaFactoryBean {

    @Inject
    private EmailDeGarantiaFactoryBean emailFactoryBean;

    public SolicitacaoDeGarantia getSolicitacaoDeGarantia() {

        SolicitacaoDeGarantia solicitacao = new SolicitacaoDeGarantiaImpl();
        EmailDeGarantia emailDeGarantia = emailFactoryBean.getEmailDeGarantia();
        solicitacao.setEmailDeGarantia(emailDeGarantia);
        solicitacao.getEmailDeGarantia().setSolicitacaoGarantia(solicitacao);

        return solicitacao;

    }

}
