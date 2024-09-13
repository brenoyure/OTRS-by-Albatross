package br.albatross.otrs.view.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.List;

import br.albatross.apis.email.Email;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import br.albatross.otrs.domain.services.emailtemplate.EmailTemplateService;
import br.albatross.otrs.externos.ChamadoRepository;
import br.albatross.otrs.externos.InventarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mail.Session;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

	@Getter @Setter
	private String bm;

	@Inject @Getter
	private SolicitacaoDeGarantia solicitacao;

	@Getter @Setter
	private Part uploadedFile;

	@Inject @Getter
	private OtrsServiceBean serviceBean;

	@Getter @Setter
	private DadosDoFornecedor fornecedorSelecionado;

	@Inject
	private ChamadoRepository chamadosRepository;

	@Getter @Setter
	private List<DadosDoChamado> chamadosDisponiveis;

	@Getter @Setter
	private int emailModeloId;

    @Inject
    private InventarioRepository repository;

    @Inject
    private EmailTemplateService emailTemplateService;

    @Resource(lookup = "java:jboss/mail/OtrsMailSession")
    private Session sessaoEmail;

	@PostConstruct
	void init() {

	    solicitacao.getEmailDeGarantia().setRemetente(sessaoEmail.getProperty("mail.smtp.user"));

	}

	public void buscarNumeroDeSeriePeloBm() {
        if (bm == null || bm.isBlank()) {
            facesContext.addMessage(null, 
                    new FacesMessage(SEVERITY_WARN, "BM deve ser informado para realizar a consulta.", null));
            return;
        }

        repository.findSerialNumberByIdentifier(bm).ifPresentOrElse(solicitacao::setNumeroDeSerie, () -> 
            facesContext.addMessage(null, 
                    new FacesMessage(SEVERITY_WARN, "Nº de Série não encontrado para o BM informado.", null))
        );

	}

    public void listarChamadosComOServicoDoFornecedor() {

        solicitacao.setDadosDoFornecedor(fornecedorSelecionado);

        List<DadosDoChamado> chamadosRelacionadosAoFornecedor = chamadosRepository.findByService(fornecedorSelecionado.getIdsDosServicosDoFornecedorNoSistemaDeChamados().stream().toList());

        if (chamadosRelacionadosAoFornecedor.isEmpty()) {

            chamadosDisponiveis = null;

            facesContext.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_WARN, 
                "Nâo há chamados disponíveis para o fornecedor selecionado", 
                "Caso tenha certeza que o(s) chamado(s) existe(m) e que está(ão) aberto(s), verifique se você definiu o serviço do chamado corretamente no Sistema de Chamados, ou se, o Serviço está corretamente associado ao fornecedor, consultando através da aba Fornecedores > Listagem, e clicando em Editar no fornecedor desejado."));

			return;
		}

        chamadosDisponiveis = chamadosRelacionadosAoFornecedor;

    }

    public void definirModeloDeEmail() {
        if (solicitacao.getChamado() == null || solicitacao.getDadosDoFornecedor() == null || solicitacao.getDescricaoDoProblema() == null || solicitacao.getDadosDoCliente() == null || solicitacao.getNumeroDeSerie() == null || solicitacao.getNumeroDeSerie().isBlank()) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN, 
                    "Campos Obrigatórios não preenchidos", 
                    "Para utilizar a opção de modelo de email pronto, favor preencher os campos obrigatórios, como Ticket, Fornecedor, Cliente, Número de Série..."));
            return;
        }

        emailTemplateService.buscarPorId(emailModeloId).ifPresent(emailTemplate -> {

            Email emailDeGarantia = solicitacao.getEmailDeGarantia();

            String assuntoDoEmail = emailTemplateService.getFromTemplate(emailTemplate.getAssunto(), solicitacao);
            String corpoDoEmail = emailTemplateService.getFromTemplate(emailTemplate.getCorpoDoEmail(), solicitacao);

            emailDeGarantia.setAssunto(assuntoDoEmail);
            emailDeGarantia.setCorpoDaMensagem(corpoDoEmail);
            
        });        

    }

    public void converterTemplatesParaStrings() {
        try {

            Email emailDeGarantia = solicitacao.getEmailDeGarantia();
            emailDeGarantia.setCorpoDaMensagem(
            emailTemplateService.getFromTemplate(emailDeGarantia.getCorpoDaMensagem(), solicitacao));

        } catch(NullPointerException e) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN, 
                    "Campos Obrigatórios não preenchidos", 
                    "Algum(s) do(s) campos obrigatórios não foram preenchidos, isso pode impedir que algumas declarações de variáveis de modelo, como $numeroDeSerie, não sejam corretamente interpretadas"));
        }

    }

    public void enviarSolicitacaoDeGarantiaPorEmail() {

        serviceBean.enviarSolicitacaoDeGarantiaPorEmail(solicitacao, uploadedFile);

    }


}
