package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.emailtemplate.EmailTemplateComboBox;
import br.albatross.otrs.persistence.repositories.emailtemplate.EmailTemplateRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaEmailTemplateAsComboBoxBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<EmailTemplateComboBox> modelosEmails;

    @Inject
    private EmailTemplateRepository repository;

    @PostConstruct
    void init() {
        modelosEmails = repository.findAllAsEmailTemplateComboBoxOrderByDescricao();
    }

}
