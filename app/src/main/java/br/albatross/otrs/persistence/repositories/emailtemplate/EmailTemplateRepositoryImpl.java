package br.albatross.otrs.persistence.repositories.emailtemplate;

import br.albatross.otrs.persistence.entities.emailpronto.EmailTemplate;
import br.albatross.otrs.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailTemplateRepositoryImpl extends RepositoryImpl<EmailTemplate, Integer>
        implements EmailTemplateRepository {

    public EmailTemplateRepositoryImpl() {
        super(EmailTemplate.class);
    }

}
