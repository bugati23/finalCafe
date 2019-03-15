package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AutoConnection;
import com.gmail.bukato23.dao.DaoFactory;
import com.gmail.bukato23.dao.GenericDao;
import com.gmail.bukato23.dao.TransactionalDaoFactory;
import com.gmail.bukato23.dao.connectionpool.ConnectionPool;
import com.gmail.bukato23.dao.connectionpool.ConnectionPoolFactory;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.Feedback;
import com.gmail.bukato23.entity.Identified;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.order.Order;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Jdbc DAO Factory
 */
public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static JdbcDaoFactory instance;
    private static Lock lock = new ReentrantLock();
    private Map<Class, Supplier<GenericDao>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private final GenericDao dao;

        DaoInvocationHandler(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
                Connection connection = connectionPool.retrieveConnection();

                TransactionManager.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.putBackConnection(connection);
                TransactionManager.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(User.class, UserDaoImpl::new);
        creators.put(Product.class, ProductDAOImpl::new);
        creators.put(Feedback.class, FeedbackDaoImpl::new);
        creators.put(Order.class, OrderImpl::new);
    }

    public static JdbcDaoFactory getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new JdbcDaoFactory();
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }
        GenericDao dao = daoCreator.get();

        return (GenericDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        return daoCreator.get();
    }
}
