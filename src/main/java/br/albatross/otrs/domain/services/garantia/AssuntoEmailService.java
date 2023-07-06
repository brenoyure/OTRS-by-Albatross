package br.albatross.otrs.domain.services.garantia;

import java.io.Serializable;
import java.util.Optional;

import jakarta.faces.view.ViewScoped;

@ViewScoped
public class AssuntoEmailService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int GARANTIA_MOUSE_DATEN    =  221;
	private static final int GARANTIA_MONITOR_DATEN  =  222;
	private static final int GARANTIA_TECLADO_DATEN  =  223;
	private static final int GARANTIA_GABINETE_DATEN =  224;

	public Optional<String> getEmailSubject(EmailGarantia emailGarantia) {
		return Optional.ofNullable(emailGarantia.getSubject());
	}

	public void setEmailSubject(EmailGarantia emailGarantia) {

		switch (emailGarantia.getTicket().getService().getId()) {
		
			case GARANTIA_MONITOR_DATEN: {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Monitor Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case GARANTIA_MOUSE_DATEN: {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Mouse Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case GARANTIA_GABINETE_DATEN: {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Computador Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case GARANTIA_TECLADO_DATEN: {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Teclado Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}
			
		}
		
	}
}
