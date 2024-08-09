package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.File;
import java.io.Serializable;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.garantia.AnexoGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioInputStreamGenerator;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolationException;

@ViewScoped
public class OtrsServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	@Inject
	private SolicitacaoDeGarantiaServiceBean solicitacaoDeGarantiaServiceBean;

	@Inject
	private AssuntoEmailDeGarantiaServiceBean assuntoEmailServiceBean;

	@Inject
	private AssinaturaEmailServiceBean assinaturaEmailServiceBean;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private FormularioInputStreamGenerator formularioFileInputStream;

	@Inject
	private AnexoGenerator anexoGenerator;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	public void definirAssuntoDoEmail(EmailDeGarantia emailGarantia) {
		assuntoEmailServiceBean.setAssuntoDoEmail(emailGarantia);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail(SolicitacaoDeGarantia solicitacao, Part uploadedFile) {

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

			var formulario = geradorFormulario.getFormulario(formularioFileInputStream.getInputStream(), solicitacao);
			vetorAnexos[0] = formulario;

			solicitacao.getEmailDeGarantia().setAnexos(vetorAnexos);

			assuntoEmailServiceBean.setAssuntoDoEmail(solicitacao.getEmailDeGarantia());
			assinaturaEmailServiceBean.setCorpoDaMensagemComAssinatura(solicitacao.getEmailDeGarantia());
			
			solicitacaoDeGarantiaServiceBean.solicitarGarantia(solicitacao);

			solicitacaoGarantiaJaEfetuada = true;

		}	catch (NotOfficeXmlFileException e) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Arquivo Inválido", "Arquivo submetido não é um formulário válido."));
		}   catch (ConstraintViolationException e) {
			e.printStackTrace();
			context.addMessage("otrs", new FacesMessage(SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}

	}

}
