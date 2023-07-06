package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.ticket.dto.TicketResumidoDto;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private String bm;

	@Getter @Setter
	private EmailGarantia emailGarantia = new EmailGarantia();

	@Getter @Setter
	private TicketResumidoDto ticketDto = new TicketResumidoDto();

	@Getter @Setter
	private Part uploadedFile;

	@Inject @Getter
	private OtrsServiceBean serviceBean;

	public void buscarNumeroDeSeriePeloBm() {
		serviceBean.buscarNumeroDeSeriePeloBm(bm, emailGarantia);
	}

	public void validarTicket(FacesContext context, UIComponent componente, Object ticket) {
		serviceBean.validarTicket(context, componente, ticket);
	}

	public void definirAssuntoDoEmail() {
		serviceBean.definirAssuntoDoEmail(emailGarantia);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		serviceBean.enviarSolicitacaoDeGarantiaPorEmail(emailGarantia, uploadedFile);
	}

}

