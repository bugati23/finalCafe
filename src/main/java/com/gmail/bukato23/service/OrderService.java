package com.gmail.bukato23.service;

import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.order.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    User makeOrder(Order order, User user, Map<Product,Integer> products) throws ServiceException;
    List<Order> getAll() throws ServiceException;
}
