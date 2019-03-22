package com.gmail.bukato23.controller.filter;

import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.util.constant.ConstantAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "blocking",urlPatterns = {"/cafe/feedback/addReview","/cafe/feedback/addReviewForm",
        "/cafe/order/addToCart","/cafe/order/cart","/cafe/order/orderForm","/cafe/order/rateOrder"},
        initParams = {@WebInitParam(name = "blockingMessage", value = "/cafe/user/blocking")})
public class BlockingFilter implements Filter {
    private String blockingMessage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        blockingMessage = filterConfig.getInitParameter("blockingMessage");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = (User) session.getAttribute(ConstantAttributes.USER);
        if (user.isBlocked()) {
            httpServletResponse.sendRedirect(blockingMessage);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
