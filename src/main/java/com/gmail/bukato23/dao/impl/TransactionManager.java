package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.GenericDao;
import com.gmail.bukato23.dao.Transaction;
import com.gmail.bukato23.dao.connectionpool.ConnectionPool;
import com.gmail.bukato23.dao.connectionpool.ConnectionPoolException;
import com.gmail.bukato23.dao.connectionpool.ConnectionPoolFactory;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    private static TransactionManager instance;
    private static Lock lock = new ReentrantLock();
    public static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);

    public TransactionManager() {
    }

    public Transaction begin() throws TransactionException {
        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
        try {
            Connection proxyConnection = connectionPool.retrieveConnection();
            proxyConnection.setAutoCommit(false);
            return new Transaction(proxyConnection);
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error(e);
            throw new TransactionException("Failed to get a connection from CP.", e);
        }
    }

    public void end(Transaction transaction) throws TransactionException {
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.putBackConnection(transaction.getProxyConnection());
            transaction.setProxyConnection(null);
        } catch (ConnectionPoolException e) {
            LOGGER.error(e);
            throw new TransactionException("Failed to close a connection.", e);
        }
    }

    public void commit(Transaction transaction) throws TransactionException {
        if (!transaction.isReadOnly()) {
            Connection proxyConnection = transaction.getProxyConnection();
            if (proxyConnection != null) {
                try {
                    proxyConnection.commit();
                } catch (SQLException e) {
                    LOGGER.error("SQLException", e);
                    throw new TransactionException("SQLException", e);
                }
            }
        }
    }

    public void rollback(Transaction transaction) throws TransactionException {
        Connection proxyConnection = transaction.getProxyConnection();
        if (proxyConnection != null) {
            try {
                proxyConnection.rollback();
            } catch (SQLException e) {
                LOGGER.error("SQLException", e);
                throw new TransactionException("SQLException", e);
            }
        }
    }


    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof GenericDao)) {    //change AbstractJdbcDao on GenericDao
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

    public static TransactionManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new TransactionManager();
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}
