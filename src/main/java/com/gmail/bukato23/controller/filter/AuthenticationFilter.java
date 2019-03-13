package com.gmail.bukato23.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/cafe/user/profile","/cafe/user/editProfile","/cafe/user/editprofileForm","/cafe/user/allUsers",
        "/cafe/user/editUser","/cafe/user/editUserForm","/cafe/user/deleteUser",
        "/cafe/feedback/addReview","/cafe/feedback/addReviewForm",
        "/cafe/product/allProducts","/cafe/product/addProduct","/cafe/product/addProductForm","/cafe/product/editProduct","/cafe/product/editProductForm","/cafe/product/deleteProduct"},
        initParams = {@WebInitParam(name = "loginPath", value = "/cafe/user/signin")})
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
        if (session == null || session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect(loginPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
