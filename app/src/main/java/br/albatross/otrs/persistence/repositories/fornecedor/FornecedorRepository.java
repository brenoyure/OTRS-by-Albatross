package br.albatross.otrs.persistence.repositories.fornecedor;

import java.util.List;

import br.albatross.otrs.persistence.entities.fornecedor.Fornecedor;
import br.albatross.otrs.persistence.repositories.Repository;

public interface FornecedorRepository extends Repository<Fornecedor, Integer> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndNotById(String nome, int id);

    List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id);

}
