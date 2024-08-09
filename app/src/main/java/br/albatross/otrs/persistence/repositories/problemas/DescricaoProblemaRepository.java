package br.albatross.otrs.persistence.repositories.problemas;

import java.util.List;

import br.albatross.otrs.persistence.entities.problemas.DescricaoProblema;

public interface DescricaoProblemaRepository {

    void persist(DescricaoProblema descricaoProblema);

    void update(DescricaoProblema descricaoProblema);

    void remove(DescricaoProblema descricaoProblema);

    List<DescricaoProblema> findAll();

}