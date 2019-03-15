package com.gmail.bukato23.controller.impl;

import com.gmail.bukato23.controller.ControllerException;
import com.gmail.bukato23.controller.RequestMappingClass;
import com.gmail.bukato23.controller.RequestMappingMethod;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.UserRole;
import com.gmail.bukato23.service.ServiceException;
import com.gmail.bukato23.service.ServiceFactory;
import com.gmail.bukato23.service.UserService;
import com.gmail.bukato23.util.BCryptHash;
import com.gmail.bukato23.util.Validation;
import com.gmail.bukato23.util.constant.*;
import com.gmail.bukato23.util.property.ConfigurationManager;
import com.gmail.bukato23.util.property.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RequestMappingClass(path = "/user")
public class UserController {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @RequestMappingMethod(path = "/profile")
    public String profile(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_PROFILE);
    }

    @RequestMappingMethod(path = "/home")
    public String home(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_HOME);
    }

    @RequestMappingMethod(path = "/signin")
    public String signIn(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
    }

    @RequestMappingMethod(path = "/signinForm")
    public String signInForm(HttpServletRequest request) throws ControllerException {
        try {
            String login = request.getParameter(ConstantParametrs.LOGIN);
            String password = request.getParameter(ConstantParametrs.PASSWORD);

            HttpSession httpSession = request.getSession();
            request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
            if (Validation.isCorrectLogin(login) && Validation.isCorrectPassword(password)) {
                User user = userService.signIn(login, password);
                if (user != null) {
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_HOME);
                    httpSession.setAttribute(ConstantAttributes.USER, user);
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN_OR_PASSWORD, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN_OR_PASSWORD));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN_OR_PASSWORD, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN_OR_PASSWORD));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/signup")
    public String signUp(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_REGISTRATION);
    }

    @RequestMappingMethod(path = "/signupForm")
    public String signUpForm(HttpServletRequest request) throws ControllerException {
        try {
            String login = request.getParameter(ConstantParametrs.LOGIN);
            String password = request.getParameter(ConstantParametrs.PASSWORD);
            String email = request.getParameter(ConstantParametrs.EMAIL);
            String firstName = request.getParameter(ConstantParametrs.FIRST_NAME);
            String lastName = request.getParameter(ConstantParametrs.LAST_NAME);

            HttpSession httpSession = request.getSession();
            request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_USER_NAME, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_REGISTRATION);
            if (Validation.isCorrectEmail(email)) {
                if (Validation.isCorrectLogin(login)) {
                    if (Validation.isCorrectPassword(password)) {
                        if (Validation.isCorrectUserName(firstName) && Validation.isCorrectUserName(lastName)) {
                            if (userService.checkIsLoginFree(login)) {
                                if (userService.checkIsEmailFree(email)) {
                                    User user = new User();
                                    user.setLogin(login);
                                    user.setPassword(BCryptHash.hashPassword(password));
                                    user.setEmail(email);
                                    user.setFirstName(firstName);
                                    user.setLastName(lastName);
                                    user.setRegistrationDate(Date.valueOf(LocalDate.now()));
                                    user.setAccount(BigDecimal.valueOf(50));
                                    user.setRole(UserRole.USER);
                                    userService.signUp(user);
                                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                                } else {
                                    request.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager
                                            .getMessage(ConstantMessages.PATH_ERROR_EXISTING_EMAIL));
                                }
                            } else {
                                request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, messageManager
                                        .getMessage(ConstantMessages.PATH_ERROR_EXISTING_LOGIN));
                            }
                        } else {
                            request.setAttribute(ConstantAttributes.ERROR_WRONG_USER_NAME, messageManager.
                                    getMessage(ConstantMessages.PATH_ERROR_WRONG_USER_NAME));
                        }
                    } else {
                        request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, messageManager.
                                getMessage(ConstantMessages.PATH_ERROR_WRONG_PASSWORD));
                    }
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager
                        .getMessage(ConstantMessages.PATH_ERROR_WRONG_EMAIL));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }


    @RequestMappingMethod(path = "/signout")
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(ConstantAttributes.USER, null);
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_HOME);
    }

    @RequestMappingMethod(path = "/forgotPassword")
    public String forgotPassword(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_RECOVERY_PASSWORD);
    }

    @RequestMappingMethod(path = "/recoveryPasswordForm")
    public String recoveryPassword(HttpServletRequest request) throws ControllerException {
        try {
            String login = request.getParameter(ConstantParametrs.LOGIN);
            String email = request.getParameter(ConstantParametrs.EMAIL);

            HttpSession httpSession = request.getSession();
            request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, null);

            httpSession.setAttribute(ConstantParametrs.LOGIN, login);
            httpSession.setAttribute(ConstantParametrs.EMAIL, email);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_RECOVERY_PASSWORD);
            if (Validation.isCorrectLogin(login) && Validation.isCorrectEmail(email)) {
                User user = userService.recoveryPassword(login, email);
                if (user != null) {
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_RECOVERY_NEW_PASSWORD);
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN_OR_PASSWORD, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN_OR_PASSWORD));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN_OR_PASSWORD, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN_OR_PASSWORD));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/recoveryNewPasswordForm")
    public String recoveryNewPassword(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            String login = (String) httpSession.getAttribute(ConstantParametrs.LOGIN);
            String newPassword = request.getParameter(ConstantParametrs.PASSWORD);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));
            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_RECOVERY_NEW_PASSWORD);
            if (Validation.isCorrectPassword(newPassword)) {
                userService.saveNewPassword(login, BCryptHash.hashPassword(newPassword));
                page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_PASSWORD));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/langen")
    public String setEnLang(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("changeLanguage", ConstantLocales.ENGLISH_LOCALE);
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_HOME);
    }

    @RequestMappingMethod(path = "/langru")
    public String setRuLang(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("changeLanguage", ConstantLocales.RUSSIAN_LOCALE);
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_HOME);
    }

    @RequestMappingMethod(path = "/editProfile")
    public String editProfile(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_PROFILE);
    }

    @RequestMappingMethod(path = "/editprofileForm")
    public String editProfileForm(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute(ConstantAttributes.USER);
            String login = request.getParameter(ConstantParametrs.LOGIN);
            if (login.isEmpty()) {
                login = user.getLogin();
            }
            String password = request.getParameter(ConstantParametrs.PASSWORD);
            if (password.isEmpty()) {
                password = user.getPassword();
            }
            String firstName = request.getParameter(ConstantParametrs.FIRST_NAME);
            if (firstName.isEmpty()) {
                firstName = user.getFirstName();
            }
            String lastName = request.getParameter(ConstantParametrs.LAST_NAME);
            if (lastName.isEmpty()) {
                lastName = user.getLastName();
            }

            request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_USER_NAME, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_PROFILE);
            if (Validation.isCorrectLogin(login)) {
                if (Validation.isCorrectPassword(password)) {
                    if (Validation.isCorrectUserName(firstName) && Validation.isCorrectUserName(lastName)) {
                        if (userService.checkIsLoginFree(login) || user.getLogin().equals(login)) {
                            User updateUser = userService.updateProfileUser(user.getId(), login, BCryptHash.hashPassword(password), firstName, lastName);
                            httpSession.setAttribute(ConstantAttributes.USER, updateUser);
                            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_PROFILE);
                        } else {
                            request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, messageManager
                                    .getMessage(ConstantMessages.PATH_ERROR_EXISTING_LOGIN));
                        }
                    } else {
                        request.setAttribute(ConstantAttributes.ERROR_WRONG_USER_NAME, messageManager.
                                getMessage(ConstantMessages.PATH_ERROR_WRONG_USER_NAME));
                    }
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_PASSWORD));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_LOGIN));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/allUsers")
    public String showAllUsers(HttpServletRequest request) throws ControllerException {
        try {
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ALL_USERS);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/editUser")
    public String editUser(HttpServletRequest request) throws ControllerException {
        try {
            int userId = Integer.parseInt(request.getParameter(ConstantAttributes.EDIT_USER));
            User editUser = userService.getByID(userId);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.EDIT_USER, editUser);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_USER);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/editUserForm")
    public String editUserForm(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute(ConstantAttributes.EDIT_USER);
            BigDecimal account = BigDecimal.valueOf(Float.parseFloat(request.getParameter(ConstantParametrs.ACCOUNT)));
            int pointsLoyalty = new Integer(request.getParameter(ConstantParametrs.POINTS_LOYALTY));
            Boolean blocked = new Boolean(request.getParameter(ConstantParametrs.STATUS_USER));
            request.setAttribute(ConstantAttributes.ERROR_WRONG_ACCOUNT, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_POINTS_LOYALTY, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_USER);
            if (Validation.isCorrectAccount(account)) {
                if (Validation.isCorrectPointsLoyalty(pointsLoyalty)) {
                    user.setAccount(account);
                    user.setPointsLoyalty(pointsLoyalty);
                    user.setBlocked(blocked);
                    userService.updateUserByAdmin(user);
                    page = ConstantURL.ALL_USERS;
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_POINTS_LOYALTY, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_POINTS_LOYALTY));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_ACCOUNT, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_ACCOUNT));
            }
            return page;
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/deleteUser")
    public String deleteUser(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute(ConstantAttributes.EDIT_USER);
            userService.deleteUser(user);
            return ConstantURL.ALL_USERS;
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }
}
