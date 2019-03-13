package com.gmail.bukato23.dao.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolImpl implements ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPoolImpl.class);
    private static ConnectionPoolImpl instance;
    private BlockingQueue<Connection> connectionQueue;
    private final int DEFAULT_POOL_SIZE = 20;
    private static Lock lock = new ReentrantLock();
    private ConnectionPoolImpl() {
        try {
            connectionQueue = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
            ConnectionProducer connectionProducer = new ConnectionProducer();
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = connectionProducer.produce();
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in ConnectionPoolImpl");
            throw new ConnectionPoolException("SQLException in ConnectionPoolImpl", e);
        }
    }

    public static ConnectionPoolImpl getInstance() {
        if (instance == null) {
            lock.lock();
                try {
                    if(instance == null) {
                        instance = new ConnectionPoolImpl();
                    }
                } finally {
                    lock.unlock();
                }
        }
        return instance;
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                connectionQueue.take().close();
            }
            deregisterDrivers();
        } catch (SQLException e) {
            LOGGER.error("SQLException");
            throw new ConnectionPoolException("SQLException", e);
        } catch (InterruptedException e){
            LOGGER.error("InterruptedException");
            throw new ConnectionPoolException("InterruptedException", e);
        }
    }
    private void deregisterDrivers() throws SQLException {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()){
            Driver driver = driverEnumeration.nextElement();
            DriverManager.deregisterDriver(driver);
        }
    }

    public Connection retrieveConnection() {
        Connection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error("Error connecting to the data source.");
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    @Override
    public void putBackConnection(Connection connection) {
        connectionQueue.offer(connection);
    }
}
