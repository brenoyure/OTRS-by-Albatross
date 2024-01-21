package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class EmailGarantiaService {

	@Inject
	private OtrsJMSQueueEmailProducer emailProducer;

	public void enviarEmailDeGarantiaParaFilaDeEnvios(@Valid EmailDeGarantia solicitacaoGarantia) {
		emailProducer.enviarEmailParaAJmsQueue(solicitacaoGarantia);
	}
}
