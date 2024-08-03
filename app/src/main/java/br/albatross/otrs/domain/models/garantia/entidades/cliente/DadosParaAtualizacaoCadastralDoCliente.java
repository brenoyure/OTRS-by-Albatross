package br.albatross.otrs.domain.models.garantia.entidades.cliente;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosParaAtualizacaoCadastralDoCliente extends DadosParaCadastroDeNovoCliente {

    @Positive
    private int id;
    
}
