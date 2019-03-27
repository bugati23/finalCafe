package com.gmail.bukato23.service;


import com.gmail.bukato23.service.impl.*;

/**
 * Service factory
 */
public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public FeedbackService getFeedbackService() {
        return new FeedbackServiceImpl();
    }

    public ProductService getProductService() {
        return new ProductServiceImpl();
    }

    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public FormService getFormService() {
        return new FormServiceImpl();
    }
}

