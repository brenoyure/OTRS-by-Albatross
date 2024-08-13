package br.albatross.otrs.persistence.repositories.cliente;

import br.albatross.otrs.persistence.entities.cliente.Cliente;
import br.albatross.otrs.persistence.repositories.Repository;

public interface ClienteRepository extends Repository<Cliente, Integer> {

    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsByNomeAndNotById(String nome, int id);

    boolean existsByDescricaoAndNotById(String descricao, int id);

}
