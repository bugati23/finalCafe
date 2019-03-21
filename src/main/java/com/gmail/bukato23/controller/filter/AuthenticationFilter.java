package com.gmail.bukato23.controller.filter;

import com.gmail.bukato23.util.constant.ConstantAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authentication",urlPatterns = {"/cafe/cafe/user/profile","/cafe/cafe/user/editProfile","/cafe/cafe/user/editprofileForm","/cafe/cafe/user/allUsers",
        "/cafe/cafe/user/editUser","/cafe/cafe/user/editUserForm","/cafe/cafe/user/deleteUser",
        "/cafe/cafe/feedback/addReview","/cafe/cafe/feedback/addReviewForm",
        "/cafe/cafe/product/allProducts","/cafe/cafe/product/addProduct","/cafe/cafe/product/addProductForm","/cafe/cafe/product/editProduct","/cafe/cafe/product/editProductForm","/cafe/cafe/product/deleteProduct",
        "/cafe/cafe/order/*"},
        initParams = {@WebInitParam(name = "loginPath", value = "/cafe/cafe/user/signin")})
public class AuthenticationFilter implements Filter {
    private String loginPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginPath = filterConfig.getInitParameter("loginPath");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session == null || session.getAttribute(ConstantAttributes.USER) == null) {
            httpServletResponse.sendRedirect(loginPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

//urlPatterns = {"/cafe/user/profile","/cafe/user/editProfile","/cafe/user/editprofileForm","/cafe/user/allUsers",
//        "/cafe/user/editUser","/cafe/user/editUserForm","/cafe/user/deleteUser",
//        "/cafe/feedback/addReview","/cafe/feedback/addReviewForm",
//        "/cafe/product/allProducts","/cafe/product/addProduct","/cafe/product/addProductForm","/cafe/product/editProduct","/cafe/product/editProductForm","/cafe/product/deleteProduct",
//        "/cafe/order/*"},