package br.albatross.otrs.persistence.repositories.problemas;

import br.albatross.otrs.persistence.entities.problemas.Problema;
import br.albatross.otrs.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ProblemaRepositoryImpl extends RepositoryImpl<Problema, Short> implements ProblemaRepository {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
    private EntityManager entityManager;

    public ProblemaRepositoryImpl() {
        super(Problema.class);
    }

}
