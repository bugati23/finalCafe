package com.gmail.bukato23.dao.impl;


import com.gmail.bukato23.dao.AbstractJdbcDao;
import com.gmail.bukato23.dao.FeedbackDao;
import com.gmail.bukato23.entity.Feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl extends AbstractJdbcDao<Feedback, Integer> implements FeedbackDao {
    private static final String SELECT_ALL_REVIEWS =
            "SELECT * FROM feedback";

    private static final String SELECT_REVIEW_BY_ID_SQL =
            "SELECT * FROM feedback WHERE id = ?";

    private static final String INSERT_REVIEW_SQL =
            "INSERT INTO feedback (user_id, review)" +
                    "VALUES (?, ?)";

    private static final String UPDATE_REVIEW =
            "UPDATE feedback SET user_id = ?, review = ? WHERE id = ?";
    private static final String DELETE_REVIEW = "DELETE FROM feedback WHERE id = ?";

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_REVIEWS;
    }

    @Override
    public String getSelectQueryId() {
        return SELECT_REVIEW_BY_ID_SQL;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_REVIEW_SQL;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_REVIEW;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_REVIEW;
    }

    @Override
    protected List<Feedback> parseResultSet(ResultSet rs) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        while (rs.next()) {
            feedbacks.add(makeEntity(rs));
        }
        return feedbacks;
    }

    private Feedback makeEntity(ResultSet rs) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setUserId(rs.getInt("user_id"));
        feedback.setReview(rs.getString("review"));
        return feedback;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Feedback feedback) throws SQLException {
        statement.setInt(1, feedback.getUserId());
        statement.setString(2, feedback.getReview());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Feedback feedback) throws SQLException {
        prepareStatementForInsert(statement, feedback);
        statement.setInt(3, feedback.getId());
    }
}
