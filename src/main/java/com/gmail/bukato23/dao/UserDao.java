package com.gmail.bukato23.dao;

import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.entity.User;

public interface UserDao extends GenericDao<User, Integer> {
    User getUserByLogin(String login, Transaction transaction) throws DaoException;

    User getUserByEmail(String email, Transaction transaction) throws DaoException;
}
