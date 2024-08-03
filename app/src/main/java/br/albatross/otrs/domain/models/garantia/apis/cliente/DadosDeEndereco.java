package br.albatross.otrs.domain.models.garantia.apis.cliente;

import java.io.Serializable;

public interface DadosDeEndereco extends Serializable {

    String getLogradouro();

    String getNumero();

    String getBairro();

    String getEstado();

    String getCidade();

    String getCep();

}
