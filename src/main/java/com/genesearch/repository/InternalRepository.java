package com.genesearch.repository;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: skiselev
 * Date: 10.09.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public interface InternalRepository<T, ID extends Serializable> {

    <S extends T> S save(S entity);

    <S extends T> Collection<S> save(Collection<S> entities);

    <S extends T> Collection<S> save(Collection<S> entities, boolean flush);

    T findById(ID id);

    boolean exists(ID id);

    void delete(ID id);

    <S extends T> void delete(S entity);

    void delete(Collection<? extends T> entities);

    <S extends T> S save(S entity, boolean flush);

}
