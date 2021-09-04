package com.alikmndlu.tamrin.base.repository.impl;

import com.alikmndlu.tamrin.base.domain.BaseDomain;
import com.alikmndlu.tamrin.base.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<E extends BaseDomain<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {

    private final EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    protected BaseRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public abstract Class<E> getModelClass();

    @Override
    public E saveOrUpdate(E e) {
        if (e.getId() != null){
            e = entityManager.merge(e);
        } else {
            entityManager.persist(e);
        }
        return e;
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    public Optional<E> findById(ID id) {
        try {
            return Optional.of(entityManager.find(getModelClass(), id));
        } catch (NoResultException noResultException){
            return Optional.empty();
        }
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery(
                "from " + getModelClass().getSimpleName(),
                getModelClass()
        ).getResultList();
    }

    @Override
    public boolean isExistById(ID id) {
        return entityManager.createQuery(
                "selec count(*) from " + getModelClass().getSimpleName() + " where id = :id",
                long.class
        ).setParameter("id", id).getSingleResult() == 1;
    }

    @Override
    public long countAll() {
        return entityManager.createQuery(
                "selec count(*) from " + getModelClass().getSimpleName(),
                long.class
        ).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
