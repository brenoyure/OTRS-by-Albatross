package br.albatross.otrs.domain.dao.apis.problemas;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;

public interface DescricaoProblemaDao {

    void persist(DescricaoProblema descricaoProblema);

    void update(DescricaoProblema descricaoProblema);

    void remove(DescricaoProblema descricaoProblema);

    List<DescricaoProblema> findAll();

}