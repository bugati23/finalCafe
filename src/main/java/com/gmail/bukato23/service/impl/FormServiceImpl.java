package com.gmail.bukato23.service.impl;

import com.gmail.bukato23.dao.DaoFactory;
import com.gmail.bukato23.dao.DaoFactoryType;
import com.gmail.bukato23.dao.FactoryProducer;
import com.gmail.bukato23.dao.FormDao;
import com.gmail.bukato23.dao.exception.DaoException;
import com.gmail.bukato23.dao.exception.PersistException;
import com.gmail.bukato23.entity.Form;
import com.gmail.bukato23.service.FormService;
import com.gmail.bukato23.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormServiceImpl implements FormService {
    private static final Logger LOGGER = LogManager.getLogger(FormServiceImpl.class);
    @Override
    public int createForm() throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            FormDao formDao = (FormDao) daoFactory.getDao(Form.class);
            Form form = formDao.persist(new Form());
            return form.getId();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get form DAO. ", e);
        }
        catch (PersistException e){
            LOGGER.error(e);
            throw new ServiceException("Failed to persist form DAO. ", e);
        }
    }

    @Override
    public boolean getById(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            FormDao formDao = (FormDao) daoFactory.getDao(Form.class);
            Form form = formDao.getByPK(id);
            return form.isFulfilled();
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get form DAO. ", e);
        }
    }

    @Override
    public void update(int id) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            FormDao formDao = (FormDao) daoFactory.getDao(Form.class);
            Form form = formDao.getByPK(id);
            form.setFulfilled(true);
            formDao.update(form);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to get form DAO. ", e);
        }
        catch (PersistException e){
            LOGGER.error(e);
            throw new ServiceException("Failed to update form DAO. ", e);
        }
    }
}
