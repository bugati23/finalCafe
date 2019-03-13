package com.gmail.bukato23.service;


import com.gmail.bukato23.service.impl.FeedbackServiceImpl;
import com.gmail.bukato23.service.impl.ProductServiceImpl;
import com.gmail.bukato23.service.impl.UserServiceImpl;

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
    public ProductService getProductService(){
        return new ProductServiceImpl();
    }
}
