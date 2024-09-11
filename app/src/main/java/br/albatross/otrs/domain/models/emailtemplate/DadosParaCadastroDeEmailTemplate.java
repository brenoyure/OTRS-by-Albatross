package br.albatross.otrs.domain.models.emailtemplate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DadosParaCadastroDeEmailTemplate {

    @NotBlank(message = "Descrição do Template Obrigatória")
    private String descricao;

    @NotBlank(message = "Assunto do Email Template Obrigatório")
    private String assunto;

    @NotBlank(message = "Corpo do Email Template Obrigatório")
    private String corpoDoEmail;

}
