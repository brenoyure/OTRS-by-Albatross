package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import br.albatross.apis.email.Anexo;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.garantia.AnexoGenerator;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailDeGarantiaService;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailDeGarantiaService;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
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
	private SolicitacaoDeGarantiaService solicitacaoDeGarantiaService;

	@Inject
	private AssuntoEmailDeGarantiaService assuntoEmailService;

	@Inject
	private AssinaturaEmailDeGarantiaService assinaturaEmailService;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private AnexoGenerator anexoGenerator;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	public void definirAssuntoDoEmail(SolicitacaoDeGarantia solicitacaoDeGarantia) {
		String assuntoDoEmailBaseadoNoServicoDoChamado = assuntoEmailService.getAssuntoDoEmailBaseadoNoServicoDoChamado(solicitacaoDeGarantia);
		solicitacaoDeGarantia.getEmailDeGarantia().setAssunto(assuntoDoEmailBaseadoNoServicoDoChamado);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail(SolicitacaoDeGarantia solicitacao, Part uploadedFile) {

		if (solicitacaoGarantiaJaEfetuada) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Solicitação de Garantia Já Realizada.", null));
			return;
		}

		try {

            Anexo[] vetorAnexos;

            if (uploadedFile == null) {
                vetorAnexos = new Anexo[1];

            } else {
                vetorAnexos = new Anexo[2];
                File uploadedAnexoFile = anexoGenerator.getAnexo(uploadedFile);
                try (InputStream uploadedFileInputStream = new BufferedInputStream(new FileInputStream(uploadedAnexoFile))) {
                    Anexo anexo = new Anexo(uploadedAnexoFile.getName(), uploadedFileInputStream.readAllBytes());
                    vetorAnexos[vetorAnexos.length - 1] = anexo;
                }
            }

            File formulario = geradorFormulario.getFormulario(solicitacao);
            try (InputStream formularioInputStream = new BufferedInputStream(new FileInputStream(formulario))) {
                Anexo anexo = new Anexo(formulario.getName(), formularioInputStream.readAllBytes());
                vetorAnexos[0] = anexo;
            }
            
            solicitacao.getEmailDeGarantia().setAnexos(vetorAnexos);

		    solicitacao.getEmailDeGarantia().setCorpoDaMensagem(assinaturaEmailService.getCorpoDoEmailComAssinatura(solicitacao));

			solicitacaoDeGarantiaService.solicitarGarantia(solicitacao);
			solicitacaoGarantiaJaEfetuada = true;

		}	catch (NotOfficeXmlFileException e) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Arquivo Inválido", "Arquivo submetido não é um formulário válido."));
		}   catch (ConstraintViolationException e) {
			e.printStackTrace();
			context.addMessage("otrs", new FacesMessage(SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}   catch(IOException e) { 
		    context.addMessage("otrs", new FacesMessage(SEVERITY_ERROR, "Erro de IO", "Ocorreu um erro ao gerar o formulário ou ao converter o arquivo submetido para anexo"));
		}

	}

}
