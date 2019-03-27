package com.gmail.bukato23.controller;

import com.gmail.bukato23.util.constant.ConstantAttributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndpointMethod {

    private static final Logger LOGGER = LogManager.getLogger(EndpointMethod.class);

    private Method method;
    private Object controller;

    private boolean isSafeMethod;

    public EndpointMethod(Method method, Object controller) {
        this.method = method;
        this.controller = controller;
    }

    public void setSafeMethod(boolean safeMethod) {
        isSafeMethod = safeMethod;
    }

    public String invoke(HttpServletRequest request) {
        try {
//            if(isSafeMethod){
//                HttpSession httpSession = request.getSession();
//                String uri = request.getRequestURI();
//                httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE,uri);
//            }
            return method.invoke(controller, request).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Problem in reflection while calling controller method");
            throw new RuntimeException("Problem in reflection while calling controller method", e);
        }

    }
}
