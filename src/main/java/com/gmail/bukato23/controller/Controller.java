package com.gmail.bukato23.controller;

import com.gmail.bukato23.controller.impl.FeedbackController;
import com.gmail.bukato23.controller.impl.ProductController;
import com.gmail.bukato23.controller.impl.UserController;

public enum  Controller {

    USER_CONTROLLER(new UserController()),
    PRODUCT_CONTROLLER(new ProductController()),
    FEEDBACK_CONTROLLER(new FeedbackController());

    private final Object instance;

    Controller(Object instance) {
        this.instance = instance;
    }

    public Object getInstance() {
        return instance;
    }
}
