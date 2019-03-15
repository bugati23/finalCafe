package com.gmail.bukato23.service.impl;

import com.gmail.bukato23.dao.*;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.dao.impl.TransactionManager;
import com.gmail.bukato23.entity.Feedback;
import com.gmail.bukato23.service.FeedbackService;
import com.gmail.bukato23.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

    @Override
    public void addFeedback(Feedback feedback) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            FeedbackDao feedbackDao = (FeedbackDao) daoFactory.getDao(Feedback.class);
            feedbackDao.persist(feedback);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get feedback DAO. ", e);
        } catch (PersistException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to persist feedback DAO. ", e);
        }
    }

    @Override
    public List<Feedback> getAll() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            FeedbackDao feedbackDao = (FeedbackDao) daoFactory.getDao(Feedback.class);
            return feedbackDao.getAll();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get feedback DAO. ", e);
        }
    }
}
