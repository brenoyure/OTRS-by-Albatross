package br.albatross.otrs.persistence.repositories.fornecedor;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.otrs.persistence.entities.Fornecedor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class FornecedoresRepositoryImpl implements FornecedorRepository {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
    private EntityManager entityManager;

    @Override
    public Fornecedor persist(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
        return fornecedor;
    }

    @Override
    public Fornecedor merge(Fornecedor fornecedor) {
        return entityManager.merge(fornecedor);
    }

    @Override
    public Optional<Fornecedor> findById(int id) {

        try {

            return Optional.of(entityManager
                                    .createQuery("SELECT f FROM Fornecedor f JOIN FETCH f.idsDosServicosDoFornecedorNoSistemaDeChamados WHERE id = ?1", Fornecedor.class)
                                    .setParameter(1, id)
                                    .getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }        

    }

    @Override
    public Fornecedor getReferenceById(int id) {
        return entityManager.getReference(Fornecedor.class, id);
    }

    @Override
    public boolean existsById(int id) {

        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT f FROM Fornecedor f WHERE f.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
        
    }

    @Override
    public boolean existsByNome(String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT f FROM Fornecedor f WHERE f.nome = ?1)", Boolean.class)
                    .setParameter(1, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public boolean existsByNomeAndNotById(String nome, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT f FROM Fornecedor f WHERE f.nome = ?1 AND f.id != ?2)", Boolean.class)
                    .setParameter(1, nome)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public List<Fornecedor> findAll() {
        return entityManager
                .createQuery("SELECT f FROM Fornecedor f JOIN FETCH f.idsDosServicosDoFornecedorNoSistemaDeChamados ORDER BY f.nome", Fornecedor.class)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @Override
    public void remove(Fornecedor fornecedor) {
        entityManager.remove(fornecedor);

    }

    @Override
    public List<Integer> findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(int id) {

        return entityManager
                .createQuery("SELECT s.idsDosServicosDoFornecedorNoSistemaDeChamados FROM Fornecedor s WHERE id = ?1", Integer.class)
                .setParameter(1, id)
                .getResultList();

    }

}
