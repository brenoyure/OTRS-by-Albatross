package br.albatross.otrs.domain.models.garantia.entidades.email;

import br.albatross.otrs.domain.models.garantia.apis.email.DadosDoEnvio;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailDeGarantiaImpl extends EmailImpl implements EmailDeGarantia {

    private static final long serialVersionUID = 1L;

    private SolicitacaoDeGarantia solicitacaoGarantia;

    public EmailDeGarantiaImpl() {

    }

    public EmailDeGarantiaImpl(DadosDoEnvio dadosDoEnvio) {
        super(dadosDoEnvio);
    }

}
