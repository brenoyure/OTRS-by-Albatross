package br.albatross.otrs.view.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
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

    @Inject
    private InventarioRepository repository;

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

	public void definirAssuntoDoEmail() {

	    if (solicitacao.getChamado() == null || solicitacao.getDadosDoFornecedor() == null || solicitacao.getDescricaoDoProblema() == null || solicitacao.getDadosDoCliente() == null) {

	        solicitacao.getEmailDeGarantia().setAssunto(null);
	        return;
	    }

	    serviceBean.definirAssuntoDoEmail(solicitacao);

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

    public void enviarSolicitacaoDeGarantiaPorEmail() {

        serviceBean.enviarSolicitacaoDeGarantiaPorEmail(solicitacao, uploadedFile);

    }


}

