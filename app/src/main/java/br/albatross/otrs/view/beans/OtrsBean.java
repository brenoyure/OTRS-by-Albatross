package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaDadosDoEnvioImpl;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaImpl;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import br.albatross.otrs.domain.services.beans.InventarioServiceBean;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
	
	@PostConstruct
	void init() {
	    solicitacao = new SolicitacaoDeGarantiaImpl();
        solicitacao.setEmailDeGarantia(new EmailDeGarantiaImpl());
        solicitacao.getEmailDeGarantia().setDadosDoEnvio(new EmailDeGarantiaDadosDoEnvioImpl());
        solicitacao.getEmailDeGarantia().setSolicitacaoGarantia(solicitacao);
	}

	public void buscarNumeroDeSeriePeloBm() {
	      inventarioServiceBean
	          .buscarNumeroDeSeriePorBm(bm)
	          .ifPresentOrElse(NdeSerie -> solicitacao.setNumeroDeSerie(NdeSerie), 
                  () -> solicitacao.setNumeroDeSerie(null));
	}
	
	public void definirAssuntoDoEmail() {
	    
	    if (solicitacao.getChamado() != null && solicitacao.getDadosDoFornecedor() != null && solicitacao.getDescricaoDoProblema() != null) {
	        
	        serviceBean.definirAssuntoDoEmail(solicitacao.getEmailDeGarantia());

	    } 
	    
	    else {

	        solicitacao.getEmailDeGarantia().setAssunto(null);

	    }
	    
	}

    public void listarChamadosComOServicoDoFornecedor() {

        solicitacao.setDadosDoFornecedor(fornecedorSelecionado);

        List<DadosDoChamado> chamadosRelacionadosAoFornecedor = chamadosDao.findByService(fornecedorSelecionado.getIdsDosServicosDoFornecedorNoSistemaDeChamados().stream().toList());

        chamadosDisponiveis = chamadosRelacionadosAoFornecedor;

    }	

    public void enviarSolicitacaoDeGarantiaPorEmail() {
        serviceBean.enviarSolicitacaoDeGarantiaPorEmail(solicitacao, uploadedFile);
    }


}

