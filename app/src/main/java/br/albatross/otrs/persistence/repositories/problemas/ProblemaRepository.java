package br.albatross.otrs.persistence.repositories.problemas;

import java.util.List;

import br.albatross.otrs.persistence.entities.problemas.Problema;

public interface ProblemaRepository {

    void persist(Problema problema);

    void update(Problema problema);

    List<Problema> findAll();

}