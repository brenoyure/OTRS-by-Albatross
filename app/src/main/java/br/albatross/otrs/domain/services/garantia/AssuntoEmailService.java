package br.albatross.otrs.domain.services.garantia;

import java.io.Serializable;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import jakarta.faces.view.ViewScoped;

@ViewScoped
public class AssuntoEmailService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int GARANTIA_MOUSE_DATEN    =  221;
	private static final int GARANTIA_MONITOR_DATEN  =  222;
	private static final int GARANTIA_TECLADO_DATEN  =  223;
	private static final int GARANTIA_GABINETE_DATEN =  224;

	public Optional<String> getEmailSubject(EmailDeGarantia emailGarantia) {
		return Optional.ofNullable(emailGarantia.getAssunto());
	}

	public Optional<String> getAssuntoDoEmailBaseadoNoServicoDoChamado(EmailDeGarantia emailGarantia) {

		if (emailGarantia.getSolicitacaoGarantia().getChamado() == null) {
			return Optional.empty();
		}

		switch (emailGarantia.getSolicitacaoGarantia().getChamado().getDadosDoServico().getIdDoServico()) {
		
			case GARANTIA_MONITOR_DATEN: {
				return Optional.of(String.format("[Ticket#%s] Problema Monitor Fabricante - Company", emailGarantia.getSolicitacaoGarantia().getChamado().getNumeroDoChamado()));
			}

			case GARANTIA_MOUSE_DATEN: {
				return Optional.of(String.format("[Ticket#%s] Problema Mouse Fabricante - Company", emailGarantia.getSolicitacaoGarantia().getChamado().getNumeroDoChamado()));
			}

			case GARANTIA_GABINETE_DATEN: {
				return Optional.of(String.format("[Ticket#%s] Problema Computador Fabricante - Company", emailGarantia.getSolicitacaoGarantia().getChamado().getNumeroDoChamado()));
			}

			case GARANTIA_TECLADO_DATEN: {
				return Optional.of(String.format("[Ticket#%s] Problema Teclado Fabricante - Company", emailGarantia.getSolicitacaoGarantia().getChamado().getNumeroDoChamado()));
			}

			default: 
				return Optional.empty();

		}
		
	}
}
