package com.alikmndlu.tamrin.base.service;

import com.alikmndlu.tamrin.base.domain.BaseDomain;
import com.sun.xml.bind.v2.model.core.ID;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseDomain<ID>, ID extends Serializable> {
    E saveOrUpdate(E e);

    void delete(E e);

    Optional<E> findById(ID id);

    List<E> findAll();

    boolean isExistById(ID id);

    long countAll();
}
