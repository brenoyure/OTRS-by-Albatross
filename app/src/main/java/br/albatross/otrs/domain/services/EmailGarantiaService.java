package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.garantia.SolicitacaoDeGarantiaService;
import br.albatross.otrs.messaging.OtrsEmailMessageProducer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class EmailGarantiaService implements SolicitacaoDeGarantiaService {

	@Inject
	private OtrsEmailMessageProducer messageProducer;

	public void enviarEmailDeGarantiaParaFilaDeEnvios(@Valid EmailDeGarantia solicitacaoGarantia) {
		messageProducer.enviarEmailParaAJmsQueue(solicitacaoGarantia);
	}

    @Override
    public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacao) {

        messageProducer.enviarEmailParaAJmsQueue(solicitacao.getEmailDeGarantia());

    }

}
