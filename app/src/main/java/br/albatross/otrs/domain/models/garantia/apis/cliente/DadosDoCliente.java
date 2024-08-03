package br.albatross.otrs.domain.models.garantia.apis.cliente;

import java.io.Serializable;

public interface DadosDoCliente extends Serializable {

    int getId();
    
    String getNome();
    String getDescricao();

    String getNumerosParaContato();
    String getEmails();

    DadosDeEndereco getDadosDeEndereco();

    HorariosDoCliente getHorarios();

}
