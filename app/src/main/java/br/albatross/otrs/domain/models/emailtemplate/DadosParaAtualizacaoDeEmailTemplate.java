package br.albatross.otrs.domain.models.emailtemplate;

import br.albatross.otrs.persistence.entities.emailtemplate.EmailTemplate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DadosParaAtualizacaoDeEmailTemplate extends DadosParaCadastroDeEmailTemplate {

    @NotNull @Positive
    private Integer id;

    public DadosParaAtualizacaoDeEmailTemplate(EmailTemplate emailTemplate) {
        super(emailTemplate.getDescricao(), emailTemplate.getAssunto(), emailTemplate.getCorpoDoEmail());
        this.id = emailTemplate.getId();
    }

    public DadosParaAtualizacaoDeEmailTemplate(Integer id, String descricao, String assunto, String corpoDoEmail) {
        super(descricao, assunto, corpoDoEmail);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
