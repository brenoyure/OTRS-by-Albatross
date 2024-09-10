package br.albatross.otrs.domain.models.emailtemplate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DadosParaCadastroDeEmailTemplate {

    @NotBlank
    private String descricao;

    @NotBlank
    private String assunto;

    @NotBlank
    private String corpoDoEmail;

}
