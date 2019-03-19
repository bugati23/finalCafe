package com.gmail.bukato23.dao.impl;

import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.OrderProductDao;
import com.gmail.bukato23.entity.OrderProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDaoImpl extends AbstractJdbcDao<OrderProduct, Integer> implements OrderProductDao {
    private static final String SELECT_ALL_ORDER_PRODUCT = "SELECT * FROM order_product";

    private static final String SELECT_ORDER_PRODUCT_BY_ID_SQL = "SELECT * FROM order_product WHERE id = ?";

    private static final String UPDATE_ORDER_PRODUCT = "UPDATE order_product SET order_id = ?, product_id = ?, amount = ? WHERE id = ?";

    private static final String INSERT_ORDER_PRODUCT_SQL = "INSERT INTO order_product (order_id, product_id, amount) VALUES (?, ?, ?)";

    private static final String DELETE_ORDER_PRODUCT = "DELETE FROM order_product WHERE id = ?";

    public OrderProductDaoImpl() {
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_ORDER_PRODUCT;
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_ORDER_PRODUCT_BY_ID_SQL;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_ORDER_PRODUCT_SQL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER_PRODUCT;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_ORDER_PRODUCT;
    }

    @Override
    public List<OrderProduct> parseResultSet(ResultSet rs) throws SQLException {
        List<OrderProduct> products = new ArrayList<>();
        while (rs.next()) {
            products.add(makeEntity(rs));
        }
        return products;
    }

    private OrderProduct makeEntity(ResultSet rs) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(rs.getInt("id"));
        orderProduct.setOrderId(rs.getInt("order_id"));
        orderProduct.setProductId(rs.getInt("product_id"));
        orderProduct.setAmount(rs.getInt("amount"));
        return orderProduct;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderProduct orderProduct) throws SQLException {
        statement.setInt(1, orderProduct.getOrderId());
        statement.setInt(2, orderProduct.getProductId());
        statement.setInt(3,orderProduct.getAmount());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderProduct orderProduct) throws SQLException {
        prepareStatementForInsert(statement, orderProduct);
        statement.setInt(4, orderProduct.getId());
    }
}
