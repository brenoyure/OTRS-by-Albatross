package br.albatross.otrs.persistence.entities.emailpronto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "email_template")
@EqualsAndHashCode(of = "id")
@Getter @Setter @NoArgsConstructor
public class EmailTemplate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", unique = true, nullable = false)
    private String descricao;

    @Column(name = "assunto", unique = false, nullable = false)
    private String assunto;

    @Column(name = "corpo_do_email", unique = false, nullable = false)
    private String corpoDoEmail;

    public EmailTemplate(String descricao, String assunto, String corpoDoEmail) {
        this.descricao = descricao;
        this.assunto = assunto;
        this.corpoDoEmail = corpoDoEmail;
    }

}
