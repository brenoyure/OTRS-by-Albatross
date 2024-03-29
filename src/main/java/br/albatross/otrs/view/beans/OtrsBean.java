package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.models.ticket.dto.TicketResumidoDto;
import br.albatross.otrs.domain.services.EmailGarantiaService;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.beans.DescricaoProblema;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailService;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailService;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioInputStreamGenerator;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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

	@Getter @Setter
	private TicketResumidoDto ticketDto = new TicketResumidoDto();

	@Inject
	private Validator<Ticket> validador;

	@Inject @Getter
	private List<Ticket> ticketsAbertosNivel1;

	@Inject
	private EmailGarantiaService emailGarantiaService;

	@Inject
	private FacesContext context;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private FormularioInputStreamGenerator formularioFileInputStream;

	@Inject
	private AssuntoEmailService assuntoEmailService;

	@Inject
	private AssinaturaEmailService assinaturaEmailService;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	@Inject @Getter
	private List<DescricaoProblema> listaDeProblemas;

	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(bm)
			.ifPresent(NdeSerie -> emailGarantia.setNumeroDeSerie(NdeSerie));
	}

	public void validarTicket(FacesContext context, UIComponent componente, Object ticket) {
		validador.validate(context, componente, (Ticket)ticket);
		emailGarantia.setTicket((Ticket)ticket);
		utilizarTextosProntos();
	}

	public void utilizarTextosProntos() {
		if (ticketsAbertosNivel1.isEmpty()) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Não há tickets abertos.", "Não há tickets abertos para a fila do Nível 1, sendo assim, não será possível a abertura de chamados de garantia."));
			return;
		}

		if (emailGarantia.getTicket() == null) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Ticket ainda não definido", "Para utilizar melhor a função de gerar os textos, por favor selecione um Ticket válido e tente novamente."));
			return;
		}
		assuntoEmailService.setAssuntoDoEmail(emailGarantia);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {

		if (solicitacaoGarantiaJaEfetuada) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Solicitação de Garantia Já Realizada.", null));
			return;
		}

		try {
			var formulario = geradorFormulario.getFormulario(formularioFileInputStream.getInputStream(), emailGarantia.getNumeroDeSerie(), emailGarantia.getBody());
			emailGarantia.setUploadedFile(formulario);
			emailGarantiaService.enviarSolicitacaoDeGarantiaParaFilaDeEnvios(emailGarantia);
			solicitacaoGarantiaJaEfetuada = true;
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail despachado para fila de envios", "E-mail despachado para a fila de envios e logo será enviado."));

		}	catch (NotOfficeXmlFileException e) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_WARN, "Arquivo Inválido", "Arquivo submetido não é um formulário válido."));
		}   catch (ConstraintViolationException e) {
			context.addMessage("otrs", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}

	}

}

