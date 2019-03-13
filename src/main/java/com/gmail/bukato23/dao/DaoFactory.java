package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.Identified;

import java.io.Serializable;

/**
 * Dao Factory
 */
public interface DaoFactory {
    /**
     * Return implementation of DAO for entity class
     *
     * @param entityClass - entity class
     * @return - implementation of DAO for entity class
     * @throws DaoException - should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getDao(Class<T> entityClass) throws DaoException;
}
