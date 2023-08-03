package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.domain.services.apis.chamados.ServicoDeChamados;
import br.albatross.otrs.domain.services.garantia.AnexoGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioGenerator;
import br.albatross.otrs.domain.services.garantia.FormularioInputStreamGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
	private InventarioServiceBean inventarioService;

	@Inject
	private ServicoDeChamados servicoDeChamados;

	@Getter
	private List<DadosDoChamado> ticketsAbertosNivel1;

	@Getter
	private List<DescricaoProblema> listaDeProblemas;

	@Inject
	private TextosProntosService textosProntosService;

	@Inject
	private EmailGarantiaServiceBean emailGarantiaServiceBean;

	@Inject
	private AssuntoEmailServiceBean assuntoEmailServiceBean;

	@Inject
	private AssinaturaEmailServiceBean assinaturaEmailServiceBean;

	@Inject
	private FormularioGenerator geradorFormulario;

	@Inject
	private FormularioInputStreamGenerator formularioFileInputStream;

	@Inject
	private AnexoGenerator anexoGenerator;

	private boolean solicitacaoGarantiaJaEfetuada = false;

	@PostConstruct
	void init() {
		ticketsAbertosNivel1 = servicoDeChamados.listarTodosOsChamadosAbertos();
		listaDeProblemas = textosProntosService.getListaDeProblemas();
	}

	public void buscarNumeroDeSeriePeloBm(String bm, SolicitacaoDeGarantia solicitacao) {
		inventarioService
		                 .buscarNumeroDeSeriePorBm(bm)
		                 .ifPresentOrElse(NdeSerie -> solicitacao.setNumeroDeSerie(NdeSerie), 
		                		 () -> solicitacao.setNumeroDeSerie(null));
	}

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

			var formulario = geradorFormulario.getFormulario(formularioFileInputStream.getInputStream(), solicitacao.getNumeroDeSerie(), solicitacao.getDescricaoDoProblema());
			vetorAnexos[0] = formulario;

			solicitacao.getEmailDeGarantia().setAnexos(vetorAnexos);
			assinaturaEmailServiceBean.setCorpoDaMensagemComAssinatura(solicitacao.getEmailDeGarantia());
			
			emailGarantiaServiceBean.enviarSolicitacaoDeGarantia(solicitacao.getEmailDeGarantia());

			solicitacaoGarantiaJaEfetuada = true;

		}	catch (NotOfficeXmlFileException e) {
			context.addMessage("otrs", new FacesMessage(SEVERITY_WARN, "Arquivo Inválido", "Arquivo submetido não é um formulário válido."));
		}   catch (ConstraintViolationException e) {
			e.printStackTrace();
			context.addMessage("otrs", new FacesMessage(SEVERITY_ERROR, e.getLocalizedMessage(), e.getMessage()));
		}
	}

}
