package br.albatross.otrs.domain.dao.apis.fornecedores;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.Fornecedor;

public interface FornecedoresDao {

    Fornecedor persist(Fornecedor fornecedor);

    Fornecedor merge(Fornecedor fornecedor);

    Optional<Fornecedor> findById(int id);

    Fornecedor getReferenceById(int id);

    boolean existsById(int id);

    List<Fornecedor> findAll();

    void remove(Fornecedor fornecedor);

    List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id);
    
}
