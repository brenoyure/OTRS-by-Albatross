package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.emailtemplate.DadosParaCadastroDeEmailTemplate;
import br.albatross.otrs.domain.services.emailpronto.EmailTemplateService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class CadastroEmailTemplateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private DadosParaCadastroDeEmailTemplate email = new DadosParaCadastroDeEmailTemplate() ;

    @Inject
    private FacesContext facesContext;

    @Inject
    private EmailTemplateService templateService;

    @Transactional
    public void cadastrar() {
        System.out.println(email.getDescricao());
        templateService.cadastrar(email);
        facesContext.addMessage(null, new FacesMessage("Email " + email.getDescricao() + "Cadastrado com sucesso"));
    }

}
