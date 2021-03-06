package com.gmail.bukato23.dao.connectionpool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection Pool Factory
 */
public class ConnectionPoolFactory {
    private static ConnectionPoolFactory instance;
    private static Lock lock = new ReentrantLock();

    private ConnectionPoolFactory() {}

    public static ConnectionPoolFactory getInstance() {
        if(instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPoolFactory();
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    public ConnectionPool getConnectionPool() {
        return ConnectionPoolImpl.getInstance();
    }
}
