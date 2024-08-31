package br.albatross.otrs.cdi;

import br.albatross.apis.email.EmailFactoryBean;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SolicitacaoDeGarantiaFactoryBean {

    @Inject
    private EmailFactoryBean emailFactoryBean;

    public SolicitacaoDeGarantia getSolicitacaoDeGarantia() {

        SolicitacaoDeGarantia solicitacao = new SolicitacaoDeGarantiaImpl();
        solicitacao.setEmailDeGarantia(emailFactoryBean.newInstance());

        return solicitacao;

    }

}
