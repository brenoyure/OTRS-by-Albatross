package br.albatross.otrs.repositories.cliente;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;

public interface ClienteRepository {

    Cliente persist(Cliente cliente);

    Cliente merge(Cliente cliente);

    List<Cliente> findAll();

    Cliente getReferenceById(int id);

    Optional<Cliente> findById(int id);

    boolean existsById(int id);

    boolean existsByNome(String nome);

    boolean existsByDescricao(String descricao);

    boolean existsByNomeAndNotById(String nome, int id);

    boolean existsByDescricaoAndNotById(String descricao, int id);

    void remove(Cliente cliente);

}
