package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.messaging.OtrsEmailMessageProducer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class SolicitacaoDeGarantiaServiceImpl implements SolicitacaoDeGarantiaService {

    @Inject
    private OtrsEmailMessageProducer messageProducer;

    @Override
    public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacao) {

        messageProducer.enviarEmailParaAJmsQueue(solicitacao.getEmailDeGarantia());

    }

}
