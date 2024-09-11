package br.albatross.otrs.persistence.entities.emailtemplate;

import jakarta.persistence.metamodel.SingularAttribute;

public abstract class EmailTemplate_ {

    public static volatile SingularAttribute<EmailTemplate, Integer> id;
    public static volatile SingularAttribute<EmailTemplate, String> descricao;
    public static volatile SingularAttribute<EmailTemplate, String> assunto;
    public static volatile SingularAttribute<EmailTemplate, String> corpoDoEmail;

}
