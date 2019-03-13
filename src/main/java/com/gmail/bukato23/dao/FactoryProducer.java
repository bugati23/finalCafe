package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.impl.JdbcDaoFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Factory producer
 * Provide DAO Factory by type
 */
public class FactoryProducer {
    private static FactoryProducer instance;
    private static Lock lock = new ReentrantLock();

    private FactoryProducer() {
    }

    public static FactoryProducer getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new FactoryProducer();
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static DaoFactory getDaoFactory(DaoFactoryType type) {
        switch (type) {
            case JDBC:
                return JdbcDaoFactory.getInstance();

            default:
                throw new EnumConstantNotPresentException(DaoFactoryType.class, "No such DaoFactory " + type.name());
        }
    }
}
