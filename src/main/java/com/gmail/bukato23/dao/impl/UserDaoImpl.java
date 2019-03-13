package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.*;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_USER_SQL =
            "INSERT INTO user (login, password, first_name, last_name, email, account, points_loyalty, blocked, registration_date, role_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL =
            "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM user";
    private static final String UPDATE_USER =
            "UPDATE user SET login = ?, password = ?, first_name = ?, last_name = ?, email = ?, account = ?, points_loyalty = ?, blocked = ?, registration_date = ?, role_id = ?  WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";

    public UserDaoImpl() {
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_USER_BY_ID_SQL;
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_USERS;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_USER_SQL;
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
    public List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(makeEntity(rs));
        }
        return users;
    }

    private User makeEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setAccount(rs.getBigDecimal("account"));
        user.setPointsLoyalty(rs.getInt("points_loyalty"));
        user.setBlocked(rs.getBoolean("blocked"));
        user.setRegistrationDate(rs.getDate("registration_date"));
        user.setRole(UserRole.fromID(rs.getInt("role_id")));
        return user;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getEmail());
        statement.setBigDecimal(6, user.getAccount());
        statement.setInt(7, user.getPointsLoyalty());
        statement.setBoolean(8, user.isBlocked());
        statement.setDate(9, user.getRegistrationDate());
        statement.setInt(10, user.getRole().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) throws SQLException {
        prepareStatementForInsert(statement, user);
        statement.setInt(11, user.getId());
    }

    @Override
    public User getUserByLogin(String login, Transaction transaction) throws DaoException {
        try (PreparedStatement ps = transaction.getProxyConnection().prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                List<User> users = parseResultSet(rs);
                if (users.size() == 0) {
                    return null;
                }
                return users.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find entity by login");
            throw new DaoException("Problem when trying to find entity by login", e);
        }
    }

    @Override
    public User getUserByEmail(String email, Transaction transaction) throws DaoException {
        try (PreparedStatement ps = transaction.getProxyConnection().prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                List<User> users = parseResultSet(rs);
                if (users.size() == 0) {
                    return null;
                }
                return users.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find entity by email");
            throw new DaoException("Problem when trying to find entity by email", e);
        }
    }
}
