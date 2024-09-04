package br.albatross.otrs.cdi;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

public class SolicitacaoDeGarantiaFactoryBean {

    @Produces @Dependent
    public SolicitacaoDeGarantia getSolicitacaoDeGarantia() {

        return new SolicitacaoDeGarantiaImpl();

    }

}
