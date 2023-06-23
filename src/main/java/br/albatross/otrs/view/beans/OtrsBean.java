package br.albatross.otrs.view.beans;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.NoSuchFileException;

import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.services.EmailGarantiaService;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
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
	
	@Inject
	private AssuntoEmailService assuntoEmailService;
	
	@Inject
	private AssinaturaEmailService assinaturaEmailService;
	
	private boolean solicitacaoGarantiaJaEfetuada = false;
	
	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(bm)
			.ifPresent(NdeSerie -> emailGarantia.setNumeroDeSerie(NdeSerie));
	}


	public void validarTicket(FacesContext context, UIComponent componente, Object value) {
		ticketService.buscarPeloNumeroDoTicket((String) value)

		.ifPresentOrElse(ticketFound -> {
				emailGarantia.setTicket(ticketFound);
				validador.validate(context, componente, (Ticket)emailGarantia.getTicket());},

				 () -> {
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Não encontrado.", "Ticket não localizado ou ainda não foi definido um serviço para o mesmo."));});
	}

	public void utilizarTextosProntos() {
		if (emailGarantia.getTicket() != null) {
			assuntoEmailService.setAssuntoDoEmail(emailGarantia);
		}
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() throws IOException {

		if (solicitacaoGarantiaJaEfetuada) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Solicitação de Garantia Já Realizada.", null));
			return;
		}

		try {
			var formulario = geradorFormulario.getFormulario(uploadedFile.getInputStream(), emailGarantia.getNumeroDeSerie(), emailGarantia.getBody());
			emailGarantia.setUploadedFile(formulario);
			emailGarantiaService.enviarSolicitacaoDeGarantiaParaFilaDeEnvios(emailGarantia);
			solicitacaoGarantiaJaEfetuada = true;
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail despachado para fila de envios", "E-mail despachado para a fila de envios e logo será enviado."));

		}   catch (NoSuchFileException e) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Formulário Não Submetido", "Submeta um arquivo de formulário e tente novamente"));
		}		
		    catch (ConstraintViolationException e) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}

	}

}

