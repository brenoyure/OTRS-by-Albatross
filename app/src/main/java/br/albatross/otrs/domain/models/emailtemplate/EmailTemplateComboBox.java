package br.albatross.otrs.domain.models.emailtemplate;

import java.util.Objects;

public class EmailTemplateComboBox {

    private final Integer id;
    private final String descricao;

    public EmailTemplateComboBox(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
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
        EmailTemplateComboBox other = (EmailTemplateComboBox) obj;
        return Objects.equals(id, other.id);
    }

}
