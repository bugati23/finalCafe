package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.order.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Integer>  {
   List<Order> getByUserId(int userId) throws DaoException;
}
