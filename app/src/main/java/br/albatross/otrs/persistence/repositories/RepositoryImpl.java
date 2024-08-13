package br.albatross.otrs.persistence.repositories;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;

@Dependent
public abstract class RepositoryImpl<T, K> implements Repository<T, K> {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
    private EntityManager entityManager;

    private final Class<T> entityClazz;

    public RepositoryImpl(Class<T> entityClazz) {
        this.entityClazz = entityClazz;
    }

    @Override
    public T persist(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T merge(T t) {
        return entityManager.merge(t);
    }

    @Override
    public List<T> findAll() {

        CriteriaQuery<T> query = 
                entityManager.getCriteriaBuilder().createQuery(entityClazz);

        query.from(entityClazz);

        return entityManager
                .createQuery(query)
                .getResultList();

    }

    @Override
    public T getReferenceById(K id) {
        return entityManager.getReference(entityClazz, id);
    }

    @Override
    public Optional<T> findById(K id) {
        return Optional.ofNullable(entityManager.find(entityClazz, id));
    }

    @Override
    public boolean existsById(K id) {

        Field[] fields = entityClazz.getDeclaredFields();
        String fieldName = null;

        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                fieldName = field.getName();
                break;
            }
        }

        String jpql = String.format("SELECT EXISTS(SELECT t FROM %s t WHERE t.%s = ?1)", entityClazz.getSimpleName(), fieldName);

        try {

            return entityManager
                    .createQuery(jpql, Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public void remove(T t) {
        entityManager.remove(t);

    }

}
