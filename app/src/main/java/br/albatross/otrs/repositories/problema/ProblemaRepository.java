package br.albatross.otrs.repositories.problema;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema;

public interface ProblemaRepository {

    void persist(Problema problema);

    void update(Problema problema);

    List<Problema> findAll();

}