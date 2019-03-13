package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.GenericDao;
import com.gmail.bukato23.dao.ProductDao;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends AbstractJdbcDao<Product, Integer> implements ProductDao {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";

    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE id = ?";

    private static final String UPDATE_PRODUCT = "UPDATE product SET title = ?, description = ?, picture = ?, price = ?, availability = ?, category_id = ? WHERE id = ?";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (title, description, picture, price, availability, category_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";

    public ProductDAOImpl() {
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_PRODUCTS;
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_PRODUCT_BY_ID_SQL;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_PRODUCT_SQL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_PRODUCT;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_PRODUCT;
    }

    @Override
    public List<Product> parseResultSet(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(makeEntity(rs));
        }
        return products;
    }

    private Product makeEntity(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setTitle(rs.getString("title"));
        product.setDescription(rs.getString("description"));
        product.setPicture(rs.getString("picture"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setAvailability(rs.getBoolean("availability"));
        product.setCategory(ProductCategory.fromID(rs.getInt("category_id")));
        return product;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getTitle());
        statement.setString(2, product.getDescription());
        statement.setString(3, product.getPicture());
        statement.setBigDecimal(4, product.getPrice());
        statement.setBoolean(5, product.isAvailability());
        statement.setInt(6, product.getCategory().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product product) throws SQLException {
        prepareStatementForInsert(statement, product);
        statement.setInt(7, product.getId());
    }
}
