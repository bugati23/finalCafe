package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.OrderProduct;

import java.util.List;

public interface OrderProductDao extends GenericDao<OrderProduct, Integer> {
    List<OrderProduct> getOrderProductsByOrderId(int id) throws DaoException;
}
