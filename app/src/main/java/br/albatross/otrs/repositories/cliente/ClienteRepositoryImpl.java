package br.albatross.otrs.repositories.cliente;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
    private EntityManager entityManager;

    @Override
    public Cliente persist(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Override
    public Cliente merge(Cliente cliente) {
        return entityManager.merge(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();
    }

    @Override
    public Cliente getReferenceById(int id) {
        return entityManager.getReference(Cliente.class, id);
    }

    @Override
    public Optional<Cliente> findById(int id) {
        return Optional.ofNullable(entityManager.find(Cliente.class, id));
    }

    @Override
    public boolean existsById(int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByNome(String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.nome = ?1)", Boolean.class)
                    .setParameter(1, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricao(String descricao) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.descricao = ?1)", Boolean.class)
                    .setParameter(1, descricao)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByNomeAndNotById(String nome, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.nome = ?1 AND c.id != ?2)", Boolean.class)
                    .setParameter(1, nome)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public boolean existsByDescricaoAndNotById(String descricao, int id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Cliente c WHERE c.descricao = ?1 AND c.id != ?2)", Boolean.class)
                    .setParameter(1, descricao)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public void remove(Cliente cliente) {
        entityManager.remove(cliente);
    }

}
