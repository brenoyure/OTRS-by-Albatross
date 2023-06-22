package br.albatross.otrs.domain.services.garantia;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AssuntoEmailService {

	public void setAssuntoDoEmail(EmailGarantia emailGarantia) {

		switch (emailGarantia.getTicket().getService().getName()) {
		
			case "Garantia DATEN::Monitor DATEN": {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Monitor Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case "Garantia DATEN::Mouse DATEN": {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Mouse Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case "Garantia DATEN::Gabinete DATEN": {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Computador Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}

			case "Garantia DATEN::Teclado DATEN": {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Teclado Fabricante - Company", emailGarantia.getTicket().getTicketNumber()));
				break;
			}
			
		}
		
	}
}
