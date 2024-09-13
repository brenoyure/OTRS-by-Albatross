package br.albatross.otrs.view.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import java.io.Serializable;

import br.albatross.otrs.domain.models.emailtemplate.DadosParaCadastroDeEmailTemplate;
import br.albatross.otrs.domain.services.emailtemplate.EmailTemplateService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.html.HtmlCommandButton;
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
public class CadastroEmailTemplateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private DadosParaCadastroDeEmailTemplate email = new DadosParaCadastroDeEmailTemplate();

    @Inject
    private FacesContext facesContext;

    @Inject
    private EmailTemplateService templateService;

    @Transactional
    public void cadastrar(AjaxBehaviorEvent event) {
        try {

            templateService.cadastrar(email);
            facesContext.addMessage(null, new FacesMessage("Email " + email.getDescricao() + "Cadastrado com sucesso"));

            if (event.getComponent() instanceof HtmlCommandButton) {
                HtmlCommandButton botaoSalvar = (HtmlCommandButton) event.getComponent();
                botaoSalvar.setValue("Email Modelo Salvo com sucesso");
                botaoSalvar.setDisabled(true);
            }

        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(SEVERITY_WARN, "Erro de validação ao cadastrar modelo", e.getMessage()));
        }

    }

}
