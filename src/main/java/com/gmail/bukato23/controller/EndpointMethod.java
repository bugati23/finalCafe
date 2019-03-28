package com.gmail.bukato23.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndpointMethod {

    private static final Logger LOGGER = LogManager.getLogger(EndpointMethod.class);

    private Method method;
    private Object controller;


    public EndpointMethod(Method method, Object controller) {
        this.method = method;
        this.controller = controller;
    }


    public String invoke(HttpServletRequest request) {
        try {
            return method.invoke(controller, request).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Problem in reflection while calling controller method");
            throw new RuntimeException("Problem in reflection while calling controller method", e);
        }

    }
}
