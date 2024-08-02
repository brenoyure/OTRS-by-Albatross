package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.cdi.SolicitacaoDeGarantiaFactoryBean;
import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.beans.InventarioServiceBean;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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

	@Getter @Setter
	private SolicitacaoDeGarantia solicitacao;

	@Getter @Setter
	private Part uploadedFile;

	@Inject @Getter
	private OtrsServiceBean serviceBean;

	@Getter @Setter
	private DadosDoFornecedor fornecedorSelecionado;

	@Inject
	private ChamadosDao chamadosDao;

	@Getter @Setter
	private List<DadosDoChamado> chamadosDisponiveis;
	
	@Inject
	private InventarioServiceBean inventarioServiceBean;

	@Inject
	private SolicitacaoDeGarantiaFactoryBean solicitacaoFactoryBean;

	@PostConstruct
	void init() {

	    solicitacao = solicitacaoFactoryBean.getSolicitacaoDeGarantia();

	}

	public void buscarNumeroDeSeriePeloBm() {
	      inventarioServiceBean
	          .buscarNumeroDeSeriePorBm(bm)
	          .ifPresentOrElse(NdeSerie -> solicitacao.setNumeroDeSerie(NdeSerie), 
                  () -> solicitacao.setNumeroDeSerie(null));
	}

	public void definirAssuntoDoEmail() {

	    if (solicitacao.getChamado() == null || solicitacao.getDadosDoFornecedor() == null || solicitacao.getDescricaoDoProblema() == null) {

	        solicitacao.getEmailDeGarantia().setAssunto(null);
	        return;
	    }

	    serviceBean.definirAssuntoDoEmail(solicitacao.getEmailDeGarantia());

	}

    public void listarChamadosComOServicoDoFornecedor() {

        solicitacao.setDadosDoFornecedor(fornecedorSelecionado);

        List<DadosDoChamado> chamadosRelacionadosAoFornecedor = chamadosDao.findByService(fornecedorSelecionado.getIdsDosServicosDoFornecedorNoSistemaDeChamados().stream().toList());

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

