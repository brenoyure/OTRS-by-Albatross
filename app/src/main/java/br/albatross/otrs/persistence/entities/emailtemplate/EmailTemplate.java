package br.albatross.otrs.persistence.entities.emailtemplate;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "email_template")
@EqualsAndHashCode(of = "id")
@Getter @Setter @NoArgsConstructor
@Cacheable
public class EmailTemplate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", unique = true, nullable = false)
    private String descricao;

    @Column(name = "assunto", unique = false, nullable = false)
    private String assunto;

    @Lob 
    @Basic(fetch = LAZY) 
    @Column(name = "corpo_do_email", unique = false, nullable = false)
    private String corpoDoEmail;

    public EmailTemplate(String descricao, String assunto, String corpoDoEmail) {
        this.descricao = descricao;
        this.assunto = assunto;
        this.corpoDoEmail = corpoDoEmail;
    }

}
