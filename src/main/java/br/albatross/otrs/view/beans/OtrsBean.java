package br.albatross.otrs.view.beans;

import java.io.IOException;
import java.io.Serializable;

import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.services.EmailGarantiaService;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigItemServiceBean service;

	@Getter @Setter
	private Ticket ticket;
	
	@Getter @Setter
	private String bm;

	@Inject
	private TicketService ticketService;

	@Getter @Setter
	private EmailGarantia emailGarantia = new EmailGarantia();

	@Inject
	private Validator<Ticket> validador;

	@Inject
	private EmailGarantiaService emailGarantiaService;

	@Inject
	private FacesContext context;

	@Getter @Setter
	private Part uploadedFile;

	@Inject
	private FormularioGenerator geradorFormulario;

	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(bm)
			.ifPresent(NdeSerie -> emailGarantia.setNumeroDeSerie(NdeSerie));
	}


	public void validarTicket(FacesContext context, UIComponent componente, Object value) {
		ticketService.buscarPeloNumeroDoTicket((String) value)

		.ifPresentOrElse(ticketFound -> {
				setTicket(ticketFound);
				validador.validate(context, componente, (Ticket)this.ticket);},

				 () -> {
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Não encontrado.", "Ticket não localizado ou ainda não foi definido um serviço para o mesmo."));});
	}

	public void upload() throws IOException {
		if (uploadedFile != null) {
			var formulario = geradorFormulario.getFormulario(uploadedFile.getInputStream(), emailGarantia.getNumeroDeSerie(), emailGarantia.getBody());
			emailGarantia.setUploadedFile(formulario);
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload de Arquivo", "Upload do arquivo " + uploadedFile.getSubmittedFileName() + " feito com sucesso."));
		}

	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		try {
			emailGarantia.setTicket(ticket);
			emailGarantiaService.enviarSolicitacaoDeGarantiaParaFilaDeEnvios(emailGarantia);
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso.", null));
		} catch (ConstraintViolationException e) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}

	}

	public void utilizarTextosProntos() {

		if (ticket != null) {
			
			if (emailGarantia.getBody().contains("Monitor")) {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Monitor Fabricante - Company", ticket.getTicketNumber()));
				return;
			}
			
			if (emailGarantia.getBody().contains("Mouse")) {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Mouse Fabricante - Company", ticket.getTicketNumber()));
				return;
			}
			
			else {
				emailGarantia.setSubject(String.format("[Ticket#%s] Problema Computador Fabricante - Company", ticket.getTicketNumber()));
				return;
			}
			
		}
		
		else {

			if (emailGarantia.getBody().contains("Monitor")) {
				emailGarantia.setSubject("Problema Monitor Fabricante - Company");
				return;
			}
			
			if (emailGarantia.getBody().contains("Mouse")) {
				emailGarantia.setSubject("Problema Mouse Fabricante - Company");
				return;
			}
			
			else {
				emailGarantia.setSubject(("Problema Computador Fabricante - Company"));
				return;
			}
		}
		
	}

}
