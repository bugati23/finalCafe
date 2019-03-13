package com.gmail.bukato23.service;


import com.gmail.bukato23.entity.User;

import java.util.List;

/**
 * Example of user service
 */
public interface UserService {

    /**
     * Sign up user
     *
     * @param user - User
     * @return - saved user
     * @throws ServiceException should be clarify
     */
    User signUp(User user) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    User recoveryPassword(String login, String email) throws ServiceException;

    void saveNewPassword(String login, String newPassword) throws ServiceException;

    User updateProfileUser(int id, String login, String password, String firstName, String lastName) throws ServiceException;

    boolean checkIsLoginFree(String login) throws ServiceException;

    boolean checkIsEmailFree(String email) throws ServiceException;

    List<User> getAll() throws ServiceException;

    User getByID(int id) throws ServiceException;

    void updateUserByAdmin(User user) throws ServiceException;

    void deleteUser(User user) throws ServiceException;

}
