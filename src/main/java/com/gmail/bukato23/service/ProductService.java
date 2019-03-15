package com.gmail.bukato23.service;

import com.gmail.bukato23.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll() throws ServiceException;

    void addProduct(Product product) throws ServiceException;

    Product getByID(int id) throws ServiceException;

    void updateProductByAdmin(Product product) throws ServiceException;

    void deleteProduct(Product product) throws ServiceException;
}
