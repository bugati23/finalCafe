package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.entity.Identified;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDao.class);

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getSelectQueryId();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    @Override
    public T getByPK(PK key, Transaction transaction) throws DaoException {
        try (PreparedStatement ps = transaction.getProxyConnection().prepareStatement(getSelectQueryId())) {
            ps.setInt(1, (Integer) key);
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs).get(0);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find entity by id");
            throw new DaoException("Problem when trying to find entity by id", e);
        }
    }

    @Override
    public List<T> getAll(Transaction transaction) throws DaoException {
        try (PreparedStatement ps = transaction.getProxyConnection().prepareStatement(getSelectQuery());
             ResultSet rs = ps.executeQuery()) {
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to find all entity");
            throw new DaoException("Problem when trying to find all entity", e);
        }
    }

    @Override
    public T persist(T object, Transaction transaction) throws PersistException {
        try (PreparedStatement preparedStatement =
                     transaction.getProxyConnection().prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, object);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getInt(1));
                    return object;
                } else {
                    throw new SQLException("Problem when creating user, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new PersistException(e.getMessage(), e);
        }
    }

    @Override
    public T update(T object, Transaction transaction) throws PersistException {
        try (PreparedStatement preparedStatement =
                     transaction.getProxyConnection().prepareStatement(getUpdateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForUpdate(preparedStatement, object);
            preparedStatement.execute();
            return object;

        } catch (SQLException e) {
            LOGGER.error("Problem when trying to update entity");
            throw new PersistException("Problem when trying to update entity", e);
        }
    }

//    @Override
//    public void update(T object,Transaction transaction) throws PersistException {
//        try (PreparedStatement preparedStatement = transaction.getProxyConnection().prepareStatement(getUpdateQuery())) {
//            prepareStatementForUpdate(preparedStatement, object);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            LOGGER.error("Problem when trying to create entity");
//            throw new PersistException("Problem when trying to create entity",e);
//        }
//    }

    @Override
    public void delete(T object, Transaction transaction) throws DaoException {
        try (PreparedStatement statement = transaction.getProxyConnection().prepareStatement(getDeleteQuery())) {
            statement.setInt(1, (Integer) object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem when trying to delete entity");
            throw new DaoException("Problem when trying to delete entity", e);
        }
    }
}
