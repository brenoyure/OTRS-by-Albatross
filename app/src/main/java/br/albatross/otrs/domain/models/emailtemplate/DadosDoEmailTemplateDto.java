package br.albatross.otrs.domain.models.emailtemplate;

import java.util.Objects;

/**
 * 
 * DTO útil com os dados básicos de um email template, para exibição em uma
 * combobox ou table, por exemplo
 * 
 * @author breno.brito
 */
public class DadosDoEmailTemplateDto {

    private final Integer id;
    private final String descricao;
    private final String assunto;

    public DadosDoEmailTemplateDto(Integer id, String descricao, String assunto) {
        this.id = id;
        this.descricao = descricao;
        this.assunto = assunto;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAssunto() {
        return assunto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DadosDoEmailTemplateDto other = (DadosDoEmailTemplateDto) obj;
        return Objects.equals(id, other.id);
    }

}
