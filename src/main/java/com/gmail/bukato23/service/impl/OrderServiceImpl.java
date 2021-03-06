package com.gmail.bukato23.service.impl;

import com.gmail.bukato23.dao.*;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.dao.impl.TransactionManager;
import com.gmail.bukato23.entity.OrderProduct;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.order.Order;
import com.gmail.bukato23.service.OrderService;
import com.gmail.bukato23.service.ServiceException;
import com.sun.javafx.collections.MappingChange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    @Override
    public User makeOrder(Order order, User user, Map<Product,Integer> products) throws ServiceException {
        TransactionalDaoFactory transactionalDaoFactory = FactoryProducer.getTransactionalDaoFactory(DaoFactoryType.JDBC);
        TransactionManager transactionManager = new TransactionManager();
        try {
            OrderDao orderDao = (OrderDao) transactionalDaoFactory.getTransactionalDao(Order.class);
            UserDao userDao = (UserDao) transactionalDaoFactory.getTransactionalDao(User.class);
            OrderProductDao orderProductDao = (OrderProductDao) transactionalDaoFactory.getTransactionalDao(OrderProduct.class);
            transactionManager.begin(orderDao,userDao,orderProductDao);
            Order saveOrder = orderDao.persist(order);
            int orderId = saveOrder.getId();
            for(Map.Entry<Product, Integer> item : products.entrySet()){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(orderId);
                orderProduct.setProductId(item.getKey().getId());
                orderProduct.setAmount(item.getValue());
                orderProductDao.persist(orderProduct);
            }
            User user1 = userDao.update(user);
            transactionManager.commit();
            return user1;
        } catch (DaoException e) {
            transactionManager.rollback();
            LOGGER.error(e);
            throw new ServiceException("Failed to get order DAO. ", e);
        } catch (PersistException e) {
            transactionManager.rollback();
            LOGGER.error(e);
            throw new ServiceException("Failed to save order. ", e);
        }
        finally {
            transactionManager.end();
        }
    }

    @Override
    public void updateOrderByAdmin(Order order) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            OrderDao orderDao = (OrderDao) daoFactory.getDao(Order.class);
            orderDao.update(order);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get order DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to update order. ", e);
        }
    }

    @Override
    public List<Order> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            OrderDao orderDao = (OrderDao) daoFactory.getDao(Order.class);
            return orderDao.getAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get order DAO. ", e);
        }
    }

    @Override
    public Order getById(int id) throws ServiceException {
            DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            try {
                OrderDao orderDao = (OrderDao) daoFactory.getDao(Order.class);
                return orderDao.getByPK(id);
            } catch (DaoException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to get order DAO. ", e);
            }
    }

    @Override
    public List<Order> getByUserId(int userId) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            OrderDao orderDao = (OrderDao) daoFactory.getDao(Order.class);
            return orderDao.getByUserId(userId);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get order DAO. ", e);
        }
    }

    @Override
    public Map<Product, Integer> getProductsByOrderId(int orderId) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            OrderProductDao orderProductDao = (OrderProductDao) daoFactory.getDao(OrderProduct.class);
            ProductDao productDao = (ProductDao) daoFactory.getDao(Product.class);
            List<OrderProduct> orderProductList = orderProductDao.getOrderProductsByOrderId(orderId);
            if(orderProductList != null) {
                Map<Product, Integer> productIntegerMap = new HashMap<>();
                for (OrderProduct elem : orderProductList) {
                    Product product = productDao.getByPK(elem.getProductId());
                    productIntegerMap.put(product, elem.getAmount());
                }
                return productIntegerMap;
            }
            return null;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get orderProduct or product DAO. ", e);
        }
    }
}
