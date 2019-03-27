package com.gmail.bukato23.service;

public interface FormService {
    int createForm() throws ServiceException;
    boolean getById(int id) throws ServiceException;
    void update(int id) throws ServiceException;
}
