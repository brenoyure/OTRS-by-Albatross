package br.albatross.otrs.persistence.repositories;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

        String jpql = String.format("SELECT EXISTS(SELECT t FROM %s t WHERE t.%s = ?1)", entityName(), primaryKeyFieldName());

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

    @Override
    public boolean deleteById(K id) {

        CriteriaBuilder criteriaBuilder = 
                entityManager.getCriteriaBuilder();

        CriteriaDelete<T> deleteEntityQuery = 
                criteriaBuilder.createCriteriaDelete(entityClazz);

        Root<T> entityRoot = 
                deleteEntityQuery.from(entityClazz);

        Predicate idEqualsToPredicate = 
                criteriaBuilder.equal(entityRoot.get(primaryKeyFieldName()), id);

        return entityManager.createQuery(deleteEntityQuery.where(idEqualsToPredicate)).executeUpdate() > 0;

    }

    private String entityName() {
        Entity entityAnnotation = 
                entityClazz.getAnnotation(Entity.class);

        return entityAnnotation.name().isBlank() ? entityClazz.getSimpleName() : entityAnnotation.name();

    }    

    private String primaryKeyFieldName() {
        for (Field field : entityClazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
                return field.getName();
            }
        }
        return null;
    }

}
