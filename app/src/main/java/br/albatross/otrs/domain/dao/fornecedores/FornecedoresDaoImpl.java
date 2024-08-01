package br.albatross.otrs.domain.dao.fornecedores;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.fornecedores.FornecedoresDao;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.Fornecedor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class FornecedoresDaoImpl implements FornecedoresDao {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
    private EntityManager entityManager;

    @Override
    public Fornecedor persist(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
        return fornecedor;
    }

    @Override
    public Fornecedor merge(Fornecedor fornecedor) {
        fornecedor = entityManager.merge(fornecedor);
        return fornecedor;
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
    public List<Fornecedor> findAll() {
        return entityManager
                .createQuery("SELECT f FROM Fornecedor f JOIN FETCH f.idsDosServicosDoFornecedorNoSistemaDeChamados ORDER BY f.nome", Fornecedor.class)
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
