package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.GenericDao;
import com.gmail.bukato23.dao.connectionpool.ConnectionPool;
import com.gmail.bukato23.dao.connectionpool.ConnectionPoolException;
import com.gmail.bukato23.dao.connectionpool.ConnectionPoolFactory;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.TransactionManagerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    public static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);
    private Connection proxyConnection;
    private List<GenericDao> abstractDaos;

    public TransactionManager() {
        abstractDaos = new ArrayList<>();
    }

    public void begin(GenericDao dao, GenericDao... daos) throws TransactionManagerException{
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        try {
            proxyConnection = connectionPool.retrieveConnection();
            proxyConnection.setAutoCommit(false);
            setConnectionWithReflection(dao, proxyConnection);
            abstractDaos.add(dao);
            for (GenericDao d : daos) {
                setConnectionWithReflection(d, proxyConnection);
                abstractDaos.add(d);
            }

        } catch (ConnectionPoolException | SQLException | DaoException e) {
            LOGGER.error(e);
           // throw new DaoException("Failed to get a connection from CP.", e);
            throw new TransactionManagerException("Failed to get a connection from CP.", e);
        }
    }

    public void end() throws TransactionManagerException {
        try {
            for (GenericDao d : abstractDaos) {
                setConnectionWithReflection(d, null);
            }
            proxyConnection.setAutoCommit(true);
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.putBackConnection(proxyConnection);
            proxyConnection = null;
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            LOGGER.error(e);
//            throw new DaoException("Failed to close a connection.", e);
            throw new TransactionManagerException("Failed to close a connection.", e);
        }
    }

    public void commit() throws TransactionManagerException {
        if (proxyConnection != null) {
            try {
                proxyConnection.commit();
            } catch (SQLException e) {
                LOGGER.error("SQLException", e);
//                throw new DaoException("Problem with  commit transaction", e);
                throw new TransactionManagerException("Problem with  commit transaction", e);
            }
        }
    }

    public void rollback() throws TransactionManagerException {
        if (proxyConnection != null) {
            try {
                proxyConnection.rollback();
            } catch (SQLException e) {
                LOGGER.error("SQLException", e);
//                throw new DaoException("Problem with  rollback transaction", e);
                throw new TransactionManagerException("Problem with  rollback transaction", e);
            }
        }
    }


    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }
}
