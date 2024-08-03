package br.albatross.otrs.domain.models.garantia.apis.cliente;

public interface DadosDoCliente {

    int getId();
    
    String getNome();
    String getDescricao();

    String getNumerosParaContato();
    String getEmails();

    DadosDeEndereco getDadosDeEndereco();

    HorariosDoCliente getHorarios();

}
