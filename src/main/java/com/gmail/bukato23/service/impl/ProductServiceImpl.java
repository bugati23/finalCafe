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

    @Override
    public List<Product> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            return productDao.getAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public void addProduct(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.persist(product);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to save product. ", e);
        }
    }

    @Override
    public Product getByID(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            return productDao.getByPK(id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        }
    }

    @Override
    public void updateProductByAdmin(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.update(product);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to update product. ", e);
        }
    }

    @Override
    public void deleteProduct(Product product) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            productDao.delete(product);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get product DAO. ", e);
        }
    }
}
