package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.AutoConnection;
import com.gmail.bukato23.dao.OrderDao;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.order.Order;
import com.gmail.bukato23.entity.order.PaymentType;
import com.gmail.bukato23.entity.order.Rating;
import com.gmail.bukato23.entity.order.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends AbstractJdbcDao<Order, Integer> implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private static final String INSERT_ORDER_SQL =
            "INSERT INTO cafe_order (user_id, payment_type_id, rating_id, status_id, total_amount, pre_oder, time_of_order, time_of_receipt)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID_SQL =
            "SELECT * FROM cafe_order WHERE id = ?";
    private static final String SELECT_ALL_ORDERS =
            "SELECT * FROM cafe_order";
    private static final String UPDATE_ORDER =
            "UPDATE cafe_order SET user_id = ?, payment_type_id = ?, rating_id = ?, status_id = ?, total_amount = ?, pre_oder = ?, time_of_order = ?, time_of_receipt = ? WHERE id = ?";
    private static final String DELETE_ORDER = "DELETE FROM cafe_order WHERE id = ?";
    private static final String SELECT_ORDERS_BY_ID_USER = "SELECT * FROM cafe_order WHERE user_id = ?";

    public OrderDaoImpl() {
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
        return UPDATE_ORDER;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_ORDER;
    }

    public String getSelectOrdersByIdUser() {
        return SELECT_ORDERS_BY_ID_USER;
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

    @AutoConnection
    @Override
    public List<Order> getByUserId(int userId) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getSelectOrdersByIdUser())) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Order> orders = parseResultSet(rs);
                if (orders.size() == 0) {
                    return null;
                }
                return orders;
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find orders by user id");
            throw new DaoException("Problem when trying to find orders by user id", e);
        }
    }
}
