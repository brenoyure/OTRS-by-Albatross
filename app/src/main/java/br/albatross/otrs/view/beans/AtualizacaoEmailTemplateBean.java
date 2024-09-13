package br.albatross.otrs.view.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;

import br.albatross.otrs.domain.models.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.otrs.domain.services.emailtemplate.EmailTemplateService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoEmailTemplateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmailTemplateService service;

    @Inject
    private FacesContext facesContext;

    @Getter @Setter
    private DadosParaAtualizacaoDeEmailTemplate email;

    @Transactional
    public void atualizar(AjaxBehaviorEvent event) {

        try {
            service.atualizar(email);
            facesContext.addMessage(null, new FacesMessage("Cadastro do " + email.getDescricao() + " atualizado com sucesso"));
        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(SEVERITY_WARN, "Dados Incorretos", e.getMessage()));
        }

    }

    public void carregarPeloId(int id) {

        service.buscarPorId(id).ifPresentOrElse(emailTemplate ->
            setEmail(new DadosParaAtualizacaoDeEmailTemplate(emailTemplate)), 
            () ->
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/administracao/emailstemplates/listagem"));
    }

    @Transactional
    public String excluirPorId() {

        facesContext.getExternalContext().getFlash().setKeepMessages(true);

        try {
            service.excluirPorId(email.getId());
            facesContext.addMessage(null, new FacesMessage("Modelo " + email.getDescricao() + " excluído com sucesso"));

        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(SEVERITY_WARN, "Dados Incorretos", e.getMessage()));
        }

        return "/administracao/emailstemplates/listagem?faces-redirect=true";

    }

}
