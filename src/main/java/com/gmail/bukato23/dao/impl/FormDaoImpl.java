package com.gmail.bukato23.dao.impl;

import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.FormDao;
import com.gmail.bukato23.entity.Form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormDaoImpl extends AbstractJdbcDao<Form, Integer> implements FormDao {
    private static final String SELECT_ALL_FORMS =
            "SELECT * FROM cafe_form";

    private static final String SELECT_FORM_BY_ID_SQL =
            "SELECT * FROM cafe_form WHERE id = ?";

    private static final String INSERT_FORM_SQL =
            "INSERT INTO cafe_form (fulfilled)" +
                    "VALUES (?)";

    private static final String UPDATE_FORM =
            "UPDATE cafe_form SET fulfilled = ? WHERE id = ?";
    private static final String DELETE_FORM = "DELETE FROM cafe_form WHERE id = ?";

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_FORMS;
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_FORM_BY_ID_SQL;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_FORM_SQL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_FORM;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_FORM;
    }

    @Override
    protected List<Form> parseResultSet(ResultSet rs) throws SQLException {
        List<Form> forms = new ArrayList<>();
        while (rs.next()) {
            forms.add(makeEntity(rs));
        }
        return forms;
    }

    private Form makeEntity(ResultSet rs) throws SQLException {
        Form form = new Form();
        form.setId(rs.getInt("id"));
        form.setFulfilled(rs.getBoolean("fulfilled"));
        return form;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Form form) throws SQLException {
        statement.setBoolean(1, form.isFulfilled());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Form form) throws SQLException {
        prepareStatementForInsert(statement, form);
        statement.setInt(2, form.getId());
    }
}
