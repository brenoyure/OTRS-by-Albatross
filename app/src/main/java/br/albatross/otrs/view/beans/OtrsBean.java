package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaDadosDoEnvioImpl;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaImpl;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private String bm;

	@Getter @Setter
	private SolicitacaoDeGarantia solicitacao;

	@Inject @Getter
	private OtrsServiceBean serviceBean;

	@PostConstruct
	void init() {
		solicitacao = new SolicitacaoDeGarantiaImpl();
		solicitacao.setEmailDeGarantia(new EmailDeGarantiaImpl());
		solicitacao.getEmailDeGarantia().setDadosDoEnvio(new EmailDeGarantiaDadosDoEnvioImpl());
		solicitacao.getEmailDeGarantia().setSolicitacaoGarantia(solicitacao);
	}

	public void buscarNumeroDeSeriePeloBm() {
		serviceBean.buscarNumeroDeSeriePeloBm(bm, solicitacao);
	}

	public void definirAssuntoDoEmail() {
		serviceBean.definirAssuntoDoEmail(solicitacao.getEmailDeGarantia());
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		serviceBean.enviarSolicitacaoDeGarantiaPorEmail(solicitacao);
	}

}

