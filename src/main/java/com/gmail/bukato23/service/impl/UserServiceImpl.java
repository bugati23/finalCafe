package com.gmail.bukato23.service.impl;


import com.gmail.bukato23.dao.*;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.dao.impl.TransactionManager;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.service.ServiceException;
import com.gmail.bukato23.service.UserService;
import com.gmail.bukato23.util.BCryptHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User signUp(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            userDao.persist(user);
            return user;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
        catch (PersistException e){
            LOGGER.error(e);
            throw new ServiceException("Failed to persist user DAO. ", e);
        }
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getUserByLogin(login);
            if (user != null && BCryptHash.checkPassword(password, user.getPassword())) {
                return user;
            }
            return null;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public boolean checkIsEmailFree(String email) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getUserByEmail(email);
            return user == null;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public boolean checkIsLoginFree(String login) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getUserByLogin(login);
            return user == null;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public User recoveryPassword(String login, String email) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getUserByLogin(login);
            if (user != null && email.equals(user.getEmail())) {
                return user;
            }
            return null;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public void saveNewPassword(String login, String newPassword) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getUserByLogin(login);
            user.setPassword(newPassword);
            userDao.update(user);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to update user. ", e);
        }
    }

    @Override
    public User updateProfileUser(int id, String login, String password, String firstName, String lastName) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getByPK(id);
            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            if (!user.getPassword().equals(password)) {
                user.setPassword(password);
            }
            user = userDao.update(user);
            return user;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to update user. ", e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            List<User> users = userDao.getAll();
            return users;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public User getByID(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            User user = userDao.getByPK(id);
            return user;
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    @Override
    public void updateUserByAdmin(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            userDao.update(user);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to update user. ", e);
        }
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            userDao.delete(user);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
}
