package br.albatross.otrs.domain.models.garantia.apis.fornecedores;

import java.util.Set;

public interface DadosDoFornecedor {

    int getId();

    String getNome();

    String getEmails();

    Set<Integer> getIdsDosServicosDoFornecedorNoSistemaDeChamados();

}
