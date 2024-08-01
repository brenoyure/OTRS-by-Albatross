package br.albatross.otrs.domain.models.garantia.entidades.solicitacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosParaNovaSolicitacaoDeGarantia {

    @NotBlank
    private String numeroDeSerie;

    @Positive
    private long idDoChamado;

    @Positive
    private int descricaoProblemaId;

    @Positive
    private int idDoFornecedor;

}
