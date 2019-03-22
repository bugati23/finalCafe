package com.gmail.bukato23.controller.filter;

import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.UserRole;
import com.gmail.bukato23.util.constant.ConstantAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authorization",urlPatterns = {"/cafe/user/allUsers",
        "/cafe/user/editUser","/cafe/user/editUserForm","/cafe/user/deleteUser",
        "/cafe/product/allProducts","/cafe/product/addProduct","/cafe/product/addProductForm","/cafe/product/editProduct","/cafe/product/editProductForm","/cafe/product/deleteProduct",
        "/cafe/order/allOrders","/cafe/order/editOrder","/cafe/order/editOrderForm"},
        initParams = {@WebInitParam(name = "authorizationMessage", value = "/cafe/user/authorization")})
public class AuthorizationFilter implements Filter {
    private String authorizationMessage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authorizationMessage = filterConfig.getInitParameter("authorizationMessage");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = (User) session.getAttribute(ConstantAttributes.USER);
        if (user.getRole() == UserRole.USER) {
            httpServletResponse.sendRedirect(authorizationMessage);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
