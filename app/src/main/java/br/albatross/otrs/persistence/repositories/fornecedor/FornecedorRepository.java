package br.albatross.otrs.persistence.repositories.fornecedor;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.persistence.entities.fornecedor.Fornecedor;

public interface FornecedorRepository {

    Fornecedor persist(Fornecedor fornecedor);

    Fornecedor merge(Fornecedor fornecedor);

    Optional<Fornecedor> findById(int id);

    Fornecedor getReferenceById(int id);

    boolean existsById(int id);

    boolean existsByNome(String nome);

    boolean existsByNomeAndNotById(String nome, int id);

    List<Fornecedor> findAll();

    void remove(Fornecedor fornecedor);

    List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id);
    
}
