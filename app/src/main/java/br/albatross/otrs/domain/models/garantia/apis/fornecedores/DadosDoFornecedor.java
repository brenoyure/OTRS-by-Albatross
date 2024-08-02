package br.albatross.otrs.domain.models.garantia.apis.fornecedores;

import java.io.Serializable;
import java.util.Set;

public interface DadosDoFornecedor extends Serializable {

    int getId();

    String getNome();

    String getEmails();

    Set<Integer> getIdsDosServicosDoFornecedorNoSistemaDeChamados();

}
