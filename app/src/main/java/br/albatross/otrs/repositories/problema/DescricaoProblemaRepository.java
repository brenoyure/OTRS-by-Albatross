package br.albatross.otrs.repositories.problema;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;

public interface DescricaoProblemaRepository {

    void persist(DescricaoProblema descricaoProblema);

    void update(DescricaoProblema descricaoProblema);

    void remove(DescricaoProblema descricaoProblema);

    List<DescricaoProblema> findAll();

}