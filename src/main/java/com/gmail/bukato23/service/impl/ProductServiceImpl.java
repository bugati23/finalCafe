package com.gmail.bukato23.service.impl;

import com.gmail.bukato23.dao.*;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.dao.impl.TransactionManager;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.service.ProductService;
import com.gmail.bukato23.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    private TransactionManager transactionManager = TransactionManager.getInstance();

    @Override
    public List<Product> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Transaction transaction = transactionManager.begin();
        transaction.setReadOnly(true);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            List<Product> products = productDao.getAll(transaction);
            transactionManager.commit(transaction);
            return products;
        } catch (DaoException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        } finally {
            transactionManager.end(transaction);
        }
    }

    @Override
    public void addProduct(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Transaction transaction = transactionManager.begin();
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.persist(product, transaction);
            transactionManager.commit(transaction);
        } catch (DaoException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } catch (PersistException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to save product. ", e);
        } finally {
            transactionManager.end(transaction);
        }
    }

    @Override
    public Product getByID(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Transaction transaction = transactionManager.begin();
        transaction.setReadOnly(true);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            Product product = productDao.getByPK(id, transaction);
            transactionManager.commit(transaction);
            return product;
        } catch (DaoException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } finally {
            transactionManager.end(transaction);
        }
    }

    @Override
    public void updateUserByAdmin(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Transaction transaction = transactionManager.begin();
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.update(product, transaction);
            transactionManager.commit(transaction);
        } catch (DaoException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } catch (PersistException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to update product. ", e);
        } finally {
            transactionManager.end(transaction);
        }
    }

    @Override
    public void deleteProduct(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Transaction transaction = transactionManager.begin();
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.delete(product, transaction);
            transactionManager.commit(transaction);
        } catch (DaoException e) {
            transactionManager.rollback(transaction);
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } finally {
            transactionManager.end(transaction);
        }
    }
}
