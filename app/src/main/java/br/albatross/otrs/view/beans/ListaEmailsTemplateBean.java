package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.services.emailpronto.EmailTemplateService;
import br.albatross.otrs.persistence.entities.emailpronto.EmailTemplate;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaEmailsTemplateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<EmailTemplate> emails;

    @Inject
    private EmailTemplateService templateService;

    @PostConstruct
    void init() {
        emails = templateService.listar();
    }

}
