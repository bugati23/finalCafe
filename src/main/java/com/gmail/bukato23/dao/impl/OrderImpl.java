package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.GenericDao;
import com.gmail.bukato23.entity.order.Order;
import com.gmail.bukato23.entity.order.PaymentType;
import com.gmail.bukato23.entity.order.Rating;
import com.gmail.bukato23.entity.order.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl extends AbstractJdbcDao<Order, Integer> implements GenericDao<Order, Integer> {
    private static final String INSERT_ORDER_SQL =
            "INSERT INTO cafe_order (user_id, payment_type_id, rating_id, status_id, total_amount, pre_oder, time_of_order, time_of_receipt)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID_SQL =
            "SELECT * FROM cafe_order WHERE id = ?";
    private static final String SELECT_ALL_ORDERS =
            "SELECT * FROM cafe_order";
    private static final String UPDATE_USER =
            "UPDATE cafe_order SET user_id = ?, payment_type_id = ?, rating_id = ?, status_id = ?, total_amount = ?, pre_oder = ?, time_of_order = ?, time_of_receipt = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM cafe_order WHERE id = ?";

    public OrderImpl() {
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_ORDER_BY_ID_SQL;
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_ORDERS;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_ORDER_SQL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_USER;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_USER;
    }

    @Override
    public List<Order> parseResultSet(ResultSet rs) throws SQLException {
        List<Order> users = new ArrayList<>();
        while (rs.next()) {
            users.add(makeEntity(rs));
        }
        return users;
    }

    private Order makeEntity(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setPaymentType(PaymentType.fromID(rs.getInt("payment_type_id")));
        order.setRating(Rating.fromID(rs.getInt("rating_id")));
        order.setStatus(Status.fromID(rs.getInt("status_id")));
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setPreOder(rs.getBoolean("pre_oder"));
        order.setDateOrder(rs.getTimestamp("time_of_order"));
        order.setDateReceipt(rs.getTimestamp("time_of_receipt"));
        return order;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getPaymentType().getId());
        statement.setInt(3, order.getRating().getId());
        statement.setInt(4, order.getStatus().getId());
        statement.setBigDecimal(5, order.getTotalAmount());
        statement.setBoolean(6, order.isPreOder());
        statement.setTimestamp(7, order.getDateOrder());
        statement.setTimestamp(8, order.getDateReceipt());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order order) throws SQLException {
        prepareStatementForInsert(statement, order);
        statement.setInt(9, order.getId());
    }
}
