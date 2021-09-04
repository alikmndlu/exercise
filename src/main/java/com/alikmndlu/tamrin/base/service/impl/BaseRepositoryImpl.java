package com.alikmndlu.tamrin.base.service.impl;

import com.alikmndlu.tamrin.base.domain.BaseDomain;
import com.alikmndlu.tamrin.base.repository.BaseRepository;
import com.alikmndlu.tamrin.base.service.BaseService;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<E extends BaseDomain<ID>, ID extends Serializable, R extends BaseRepository<E, ID>>
        implements BaseService<E, ID> {

    protected final R repository;

    protected BaseRepositoryImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E saveOrUpdate(E e) {
        EntityManager entityManager = repository.getEntityManager();
        entityManager.getTransaction().begin();
        e = repository.saveOrUpdate(e);
        entityManager.getTransaction().commit();
        return e;
    }

    @Override
    public void delete(E e) {
        EntityManager entityManager = repository.getEntityManager();
        entityManager.getTransaction().begin();
        repository.delete(e);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<E> findById(ID id) {
        repository.getEntityManager();
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        repository.getEntityManager();
        return repository.findAll();
    }

    @Override
    public boolean isExistById(ID id) {
        repository.getEntityManager();
        return repository.isExistById(id);
    }

    @Override
    public long countAll() {
        repository.getEntityManager();
        return repository.countAll();
    }
}
