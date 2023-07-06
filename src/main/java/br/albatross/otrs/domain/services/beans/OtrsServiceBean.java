package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.services.garantia.AnexoGenerator;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioInputStreamGenerator;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;

@ViewScoped
public class OtrsServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	@Inject
	private ConfigItemServiceBean configItemService;
	
	@Inject @Getter
	private List<Ticket> ticketsAbertosNivel1;

	@Inject @Getter
	private List<DescricaoProblema> listaDeProblemas;

	@Inject
	private Validator<Ticket> ticketValidator;

	@Inject
	private EmailGarantiaServiceBean emailGarantiaServiceBean;

	@Inject
	private AssuntoEmailServiceBean assuntoEmailServiceBean;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private FormularioInputStreamGenerator formularioFileInputStream;

	@Inject
	private AnexoGenerator anexoGenerator;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	public void buscarNumeroDeSeriePeloBm(String bm, EmailGarantia emailGarantia) {
		configItemService
		                 .buscarNumeroDeSeriePorBm(bm)
		                 .ifPresentOrElse(NdeSerie -> emailGarantia.setNumeroDeSerie(NdeSerie), 
		                		 () -> emailGarantia.setNumeroDeSerie(null));
	}

	public void validarTicket(FacesContext context, UIComponent component, Object ticket) {
		ticketValidator.validate(context, component, (Ticket)ticket);
	}

	public void definirAssuntoDoEmail(EmailGarantia emailGarantia) {
		if (ticketsAbertosNivel1.isEmpty()) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Não há tickets abertos.", "Não há tickets abertos para a fila do Nível 1, sendo assim, não será possível a abertura de chamados de garantia."));
			return;
		}

		assuntoEmailServiceBean.setAssuntoDoEmail(emailGarantia);

	}

	public void enviarSolicitacaoDeGarantiaPorEmail(EmailGarantia emailGarantia, Part uploadedFile) {

		if (solicitacaoGarantiaJaEfetuada) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Solicitação de Garantia Já Realizada.", null));
			return;
		}

		try {

			File[] vetorAnexos;

			if (uploadedFile == null) {
				vetorAnexos = new File[1];

			} else {
				vetorAnexos = new File[2];
				vetorAnexos[1] = anexoGenerator.getAnexo(uploadedFile);
			}

			var formulario = geradorFormulario.getFormulario(formularioFileInputStream.getInputStream(), emailGarantia.getNumeroDeSerie(), emailGarantia.getBody());
			vetorAnexos[0] = formulario;

			emailGarantia.setUploadedFiles(vetorAnexos);
			emailGarantiaServiceBean.enviarSolicitacaoDeGarantia(emailGarantia);

			solicitacaoGarantiaJaEfetuada = true;

		}	catch (NotOfficeXmlFileException e) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Arquivo Inválido", "Arquivo submetido não é um formulário válido."));
		}   catch (ConstraintViolationException e) {
			e.printStackTrace();
			context.addMessage("otrs", new FacesMessage(SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}
	}

}
