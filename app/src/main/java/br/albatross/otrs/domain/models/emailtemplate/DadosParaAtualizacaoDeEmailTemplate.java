package br.albatross.otrs.domain.models.emailtemplate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DadosParaAtualizacaoDeEmailTemplate extends DadosParaCadastroDeEmailTemplate {

    @NotNull @Positive
    private Integer id;

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
