package br.albatross.otrs.domain.services;

import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class EmailGarantiaService {

	@Inject
	private OtrsJMSQueueEmailProducer emailProducer;

	public void enviarSolicitacaoDeGarantiaParaFilaDeEnvios(@Valid EmailGarantia emailGarantia) {
		emailProducer.enviarEmailParaAJmsQueue(emailGarantia);
	}
}
