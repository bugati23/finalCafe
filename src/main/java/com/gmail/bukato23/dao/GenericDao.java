package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.entity.Identified;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Entity primary key
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
    /**
     * Save identified entity in DB
     *
     * @param object identified entity
     * @return identified entity in DB
     * @throws PersistException should be clarify
     */
    T persist(T object, Transaction transaction) throws PersistException;

    /**
     * Get identified entity by PK
     *
     * @param id id
     * @return identified entity
     * @throws DaoException should be clarify
     */
    T getByPK(PK id, Transaction transaction) throws DaoException;

    /**
     * Update identified entity
     *
     * @param object identified entity
     * @throws PersistException should be clarify
     */
    T update(T object, Transaction transaction) throws PersistException;

    /**
     * Delete identified entity
     *
     * @param object identified entity
     * @throws PersistException should be clarify
     */
    void delete(T object, Transaction transaction) throws DaoException;

    /**
     * Get all identified entity
     *
     * @return identified entity
     * @throws DaoException should be clarify
     */
    List<T> getAll(Transaction transaction) throws DaoException;
}