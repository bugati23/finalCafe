package com.gmail.bukato23.controller.impl;

import com.gmail.bukato23.controller.ControllerException;
import com.gmail.bukato23.controller.RequestMappingClass;
import com.gmail.bukato23.controller.RequestMappingMethod;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.order.PaymentType;
import com.gmail.bukato23.service.ProductService;
import com.gmail.bukato23.service.ServiceException;
import com.gmail.bukato23.service.ServiceFactory;
import com.gmail.bukato23.util.Validation;
import com.gmail.bukato23.util.constant.*;
import com.gmail.bukato23.util.property.ConfigurationManager;
import com.gmail.bukato23.util.property.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMappingClass(path = "/order")
public class OrderController {
    private ProductService productService = ServiceFactory.getInstance().getProductService();

    @RequestMappingMethod(path = "/addToCart")
    public String addToCart(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            Map<Product, Integer> products = (Map<Product, Integer>) httpSession.getAttribute(ConstantAttributes.CART);
            if (products == null) {
                products = new HashMap<>();
            }
            Product product = productService.getByID(Integer.parseInt(request.getParameter(ConstantParametrs.PRODUCT_TO_CART)));
            String amountStr = request.getParameter(ConstantParametrs.AMOUNT_OF_PRODUCT);

            request.setAttribute(ConstantAttributes.ERROR_WRONG_AMOUNT, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));

            if (Validation.isCorrectAmount(amountStr)) {
                int amount = Integer.parseInt(amountStr);
                if (products.containsKey(product)) {
                    products.computeIfPresent(product, (k, v) -> v + amount);
                } else {
                    products.put(product, amount);
                }
                httpSession.setAttribute(ConstantAttributes.CART, products);
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_AMOUNT, messageManager
                        .getMessage(ConstantMessages.PATH_ERROR_WRONG_AMOUNT));
            }
            return ConstantURL.MENU;
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/cart")
    public String showCart(HttpServletRequest request) throws ControllerException {
        HttpSession httpSession = request.getSession();
        Map<Product, Integer> products = (Map<Product, Integer>) httpSession.getAttribute(ConstantAttributes.CART);
        httpSession.setAttribute(ConstantAttributes.TOTAL_PRICE, null);
        if (products != null) {
            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal productCost = BigDecimal.ZERO;
            for (Map.Entry<Product, Integer> item : products.entrySet()) {
                productCost = item.getKey().getPrice().multiply(new BigDecimal(item.getValue()));
                totalPrice = totalPrice.add(productCost);
            }
            httpSession.setAttribute(ConstantAttributes.TOTAL_PRICE, totalPrice);
        }

        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CART);
    }

    @RequestMappingMethod(path = "/deleteProduct")
    public String deleteProduct(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            Map<Product, Integer> products = (Map<Product, Integer>) httpSession.getAttribute(ConstantAttributes.CART);
            Product product = productService.getByID(Integer.parseInt(request.getParameter(ConstantParametrs.DELETE_PRODUCT)));
            products.remove(product);
            if (products.size() == 0) {
                products = null;
            }
            httpSession.setAttribute(ConstantAttributes.CART, products);
            return "redirect " + ConstantURL.CART;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/checkout")
    public String checkout(HttpServletRequest request) throws ControllerException {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CHECKOUT);
    }

    @RequestMappingMethod(path = "/orderForm")
    public String makeOrder(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            Map<Product, Integer> products = (Map<Product, Integer>) httpSession.getAttribute(ConstantAttributes.CART);
            BigDecimal total = (BigDecimal) httpSession.getAttribute(ConstantAttributes.TOTAL_PRICE);
            User user = (User) httpSession.getAttribute(ConstantAttributes.USER);
            Timestamp timeOrder = new Timestamp(System.currentTimeMillis());
            PaymentType paymentType = PaymentType.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.PAYMENT_TYPE)));
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date date = formatter.parse(request.getParameter(ConstantParametrs.TIME_RECEIPT));
            Timestamp timeReceipt = new Timestamp(date.getTime());
            boolean usePoints = Boolean.parseBoolean(request.getParameter(ConstantParametrs.USE_POINTS));

            request.setAttribute(ConstantAttributes.ERROR_WRONG_TIME, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PAYMENT_TYPE,null);
            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));
            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CHECKOUT);
            if (Validation.isCorrectTimeReciept(timeOrder, timeReceipt)) {
                double factor = 1;
                int points = user.getPointsLoyalty();
                if (usePoints && points != 0) {
                    factor = 1.0 * points / 100;
                    points = 0;
                }
                if(paymentType == PaymentType.ONLINE_ACCOUNT){
                    if(user.getAccount().compareTo(total.multiply(BigDecimal.valueOf(factor))) >= 0){

                    }
                    else{
                        request.setAttribute(ConstantAttributes.ERROR_WRONG_PAYMENT_TYPE, messageManager
                                .getMessage(ConstantMessages.PATH_ERROR_WRONG_PAYMENT_TYPE));
                    }
                }
                else {

                }

            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_TIME, messageManager
                        .getMessage(ConstantMessages.PATH_ERROR_WRONG_TIME));
            }
            return page;
        } catch (ParseException e) {
            throw new ControllerException(e);
        }
    }
}
