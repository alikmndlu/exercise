package com.alikmndlu.tamrin.base.repository;

import com.alikmndlu.tamrin.base.domain.BaseDomain;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseDomain<ID>, ID extends Serializable> {
    E saveOrUpdate(E e);

    void delete(E e);

    Optional<E> findById(ID id);

    List<E> findAll();

    boolean isExistById(ID id);

    long countAll();

    EntityManager getEntityManager();
}
