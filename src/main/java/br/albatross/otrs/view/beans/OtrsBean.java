package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaDadosDoEnvioImpl;
import br.albatross.otrs.domain.models.garantia.entidades.email.EmailDeGarantiaImpl;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import br.albatross.otrs.domain.services.beans.OtrsServiceBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
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

	@Getter @Setter
	private String bm;

	@Getter @Setter
	private SolicitacaoGarantia solicitacao;

	@Getter @Setter
	private Part uploadedFile;

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

	public void validarTicket(FacesContext context, UIComponent componente, Object ticket) {
		serviceBean.validarTicket(context, componente, ticket);
	}

	public void definirAssuntoDoEmail() {
		serviceBean.definirAssuntoDoEmail(solicitacao.getEmailDeGarantia());
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		serviceBean.enviarSolicitacaoDeGarantiaPorEmail(solicitacao, uploadedFile);
	}

}

