package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import br.albatross.apis.email.Email;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.garantia.AssinaturaEmailDeGarantiaService;
import br.albatross.otrs.domain.services.garantia.AssuntoEmailDeGarantiaService;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.SolicitacaoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;

@ViewScoped
public class OtrsServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;

	@Inject
	private SolicitacaoService solicitacaoService;

	@Inject
	private AssuntoEmailDeGarantiaService assuntoEmailService;

	@Inject
	private AssinaturaEmailDeGarantiaService assinaturaEmailService;

	@Inject
	private FormularioGenerator geradorFormulario;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	public void definirAssuntoDoEmail(SolicitacaoDeGarantia solicitacaoDeGarantia) {
		String assuntoDoEmailBaseadoNoServicoDoChamado = assuntoEmailService.getAssuntoDoEmailBaseadoNoServicoDoChamado(solicitacaoDeGarantia);
		solicitacaoDeGarantia.getEmailDeGarantia().setAssunto(assuntoDoEmailBaseadoNoServicoDoChamado);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail(SolicitacaoDeGarantia solicitacao, Part uploadedFile) {

		if (solicitacaoGarantiaJaEfetuada) {
			context.addMessage(null,
					new FacesMessage(SEVERITY_WARN, "Solicitação de Garantia Já Realizada.", null));
			return;
		}

		try {

		    Email emailDeGarantia = solicitacao.getEmailDeGarantia();

		    File formularioDeGarantia = geradorFormulario.getFormulario(solicitacao);
		    emailDeGarantia.adicionarAnexo(formularioDeGarantia);

		    if (uploadedFile != null) {
		        emailDeGarantia.adicionarAnexo(uploadedFile.getSubmittedFileName(), uploadedFile.getInputStream());
		    }

		    String corpoDoEmail = assinaturaEmailService.getCorpoDoEmailComAssinatura(solicitacao);
		    emailDeGarantia.setCorpoDaMensagem(corpoDoEmail);

		    solicitacaoService.solicitarGarantia(solicitacao);
		    solicitacaoGarantiaJaEfetuada = true;

	        context.addMessage(null, 
	                new FacesMessage(SEVERITY_INFO, 
	                        "Solicitação despachada para fila de envios", 
	                        "Você pode conferir o status da Solicitação através do Sistema de Chamados, ou na Caixa de Entrada dos e-mails que receberam cópia. Lembrando que Sistemas de Chamados podem levar alguns minutos para registrarem a Solicitação."));		    

		}   catch(IOException e) {
		    context.addMessage(null, 
		            new FacesMessage(SEVERITY_ERROR, 
		                    "Erro de IO", 
		                    "Ocorreu um erro ao gerar o formulário ou ao converter o arquivo submetido para anexo"));
		}

	}

}
