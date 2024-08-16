package br.albatross.otrs.persistence.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    T persist(T t);

    T merge(T t);

    List<T> findAll();

    T getReferenceById(K id);

    Optional<T> findById(K id);

    boolean existsById(K id);

    void remove(T t);

    boolean deleteById(K id);

}