package com.gmail.bukato23.controller.impl;

import com.gmail.bukato23.controller.ControllerException;
import com.gmail.bukato23.controller.RequestMappingClass;
import com.gmail.bukato23.controller.RequestMappingMethod;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.entity.order.Order;
import com.gmail.bukato23.entity.order.PaymentType;
import com.gmail.bukato23.entity.order.Rating;
import com.gmail.bukato23.entity.order.Status;
import com.gmail.bukato23.service.*;
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
import java.util.*;

@RequestMappingClass(path = "/order")
public class OrderController {
    private ProductService productService = ServiceFactory.getInstance().getProductService();
    private OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private FormService formService = ServiceFactory.getInstance().getFormService();

    @RequestMappingMethod(path = "/addToCart")
    public String addToCart(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            int formId = (Integer) httpSession.getAttribute(ConstantParametrs.FORM_ID);
            if (!formService.getById(formId)) {
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
                formService.update(formId);
                return "redirect " + ConstantURL.CART;
            }
            return "redirect " + ConstantURL.CART;
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/cart")
    public String showCart(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.CART);
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
            if (products != null && products.containsKey(product)) {
                products.remove(product);
                if (products.size() == 0) {
                    products = null;
                }
            }
            httpSession.setAttribute(ConstantAttributes.CART, products);
            return "redirect " + ConstantURL.CART;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/checkout")
    public String checkout(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.CHECKOUT);
            int formId = formService.createForm();
            request.setAttribute(ConstantParametrs.FORM_ID, formId);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CHECKOUT);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/orderForm")
    public String makeOrder(HttpServletRequest request) throws ControllerException {
        try {
            int formId = Integer.parseInt(request.getParameter(ConstantParametrs.FORM_ID));
            if (!formService.getById(formId)) {
                HttpSession httpSession = request.getSession();
                Map<Product, Integer> products = (Map<Product, Integer>) httpSession.getAttribute(ConstantAttributes.CART);
                BigDecimal total = (BigDecimal) httpSession.getAttribute(ConstantAttributes.TOTAL_PRICE);
                User user = (User) httpSession.getAttribute(ConstantAttributes.USER);
                Timestamp timeOrder = new Timestamp(System.currentTimeMillis());
                PaymentType paymentType = PaymentType.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.PAYMENT_TYPE)));
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date = formatter.parse(request.getParameter(ConstantParametrs.TIME_RECEIPT));
                Timestamp timeReceipt = new Timestamp(date.getTime());
                boolean usePoints = Boolean.parseBoolean(request.getParameter(ConstantParametrs.USE_POINTS));

                request.setAttribute(ConstantAttributes.ERROR_WRONG_TIME, null);
                request.setAttribute(ConstantAttributes.ERROR_WRONG_PAYMENT_TYPE, null);
                MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                        ConstantAttributes.CHANGE_LANGUAGE));
                String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CHECKOUT);
                if (Validation.isCorrectTimeReciept(timeOrder, timeReceipt)) {
                    double factor = 1;
                    int points = user.getPointsLoyalty();
                    if (usePoints && points != 0) {
                        factor = 1.0 * points / 100;
                        user.setPointsLoyalty(0);
                    }
                    total = total.multiply(BigDecimal.valueOf(factor));
                    if (paymentType == PaymentType.ONLINE_ACCOUNT) {
                        if (user.getAccount().compareTo(total) >= 0) {
                            page = makeOrderCommon(httpSession, products, user, paymentType, total, timeOrder, timeReceipt);
                            formService.update(formId);
                        } else {
                            request.setAttribute(ConstantAttributes.ERROR_WRONG_PAYMENT_TYPE, messageManager
                                    .getMessage(ConstantMessages.PATH_ERROR_WRONG_PAYMENT_TYPE));
                        }
                    } else {
                        page = makeOrderCommon(httpSession, products, user, paymentType, total, timeOrder, timeReceipt);
                        formService.update(formId);
                    }

                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_TIME, messageManager
                            .getMessage(ConstantMessages.PATH_ERROR_WRONG_TIME));
                }
                return page;
            }
            return "redirect " + ConstantURL.PROFILE;
        } catch (ParseException | ServiceException e) {
            throw new ControllerException(e);
        }
    }

    private String makeOrderCommon(HttpSession httpSession, Map<Product, Integer> products, User user, PaymentType paymentType, BigDecimal total, Timestamp timeOrder, Timestamp timeReceipt) throws ServiceException {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setPaymentType(paymentType);
        order.setRating(Rating.DEFAULT);
        order.setStatus(Status.EXPECTS);
        order.setTotalAmount(total);
        if (Validation.isPreOder(timeOrder, timeReceipt)) {
            order.setPreOder(true);
            user.setPointsLoyalty(user.getPointsLoyalty() + 2);
        } else {
            order.setPreOder(false);
        }
        order.setDateOrder(timeOrder);
        order.setDateReceipt(timeReceipt);
        if (paymentType == PaymentType.ONLINE_ACCOUNT) {
            user.setAccount(user.getAccount().subtract(total));
        }
        user = orderService.makeOrder(order, user, products);
        httpSession.setAttribute(ConstantAttributes.USER, user);
        httpSession.setAttribute(ConstantAttributes.CART, null);
        httpSession.setAttribute(ConstantAttributes.TOTAL_PRICE, null);
        httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, "/cafe/user/profile");
        return "redirect " + ConstantURL.PROFILE;
    }

    @RequestMappingMethod(path = "/allOrders")
    public String showAllOders(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.ALL_ORDERS);
            List<Order> allOrders = orderService.getAll();
            List<User> userOrder = new ArrayList<>();
            for (Order order : allOrders) {
                User user = userService.getByID(order.getUserId());
                userOrder.add(user);
            }
            List<Map<Product, Integer>> myProductsForOrders = new ArrayList<>();
            for (Order order : allOrders) {
                Map<Product, Integer> productIntegerMap = orderService.getProductsByOrderId(order.getId());
                myProductsForOrders.add(productIntegerMap);
            }
            request.setAttribute(ConstantAttributes.MY_PRODUCTS, myProductsForOrders);
            request.setAttribute(ConstantParametrs.ALL_ORDERS, allOrders);
            request.setAttribute(ConstantParametrs.USER_ODER, userOrder);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ALL_ORDERS);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/editOrder")
    public String editOrder(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            int orderId = Integer.parseInt(request.getParameter(ConstantAttributes.EDIT_ORDER));
            Order editOrder = orderService.getById(orderId);
            httpSession.setAttribute(ConstantAttributes.EDIT_ORDER, editOrder);
            int formId = formService.createForm();
            request.setAttribute(ConstantParametrs.FORM_ID, formId);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_ORDER);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/editOrderForm")
    public String editOrderForm(HttpServletRequest request) throws ControllerException {
        try {
            int formId = Integer.parseInt(request.getParameter(ConstantParametrs.FORM_ID));
            if (!formService.getById(formId)) {
                HttpSession httpSession = request.getSession();
                Order order = (Order) httpSession.getAttribute(ConstantAttributes.EDIT_ORDER);
                Status orderStatus = Status.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.STATUS_ORDER)));
                order.setStatus(orderStatus);
                orderService.updateOrderByAdmin(order);
                formService.update(formId);
                return "redirect " + ConstantURL.ALL_ORDERS;
            }
            return "redirect " + ConstantURL.ALL_ORDERS;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/myOrders")
    public String showMyOrders(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.MY_ORDERS);
            User user = (User) httpSession.getAttribute(ConstantAttributes.USER);
            List<Order> myOrders = orderService.getByUserId(user.getId());
            List<Map<Product, Integer>> myProductsForOrders = new ArrayList<>();
            for (Order order : myOrders) {
                Map<Product, Integer> productIntegerMap = orderService.getProductsByOrderId(order.getId());
                myProductsForOrders.add(productIntegerMap);
            }
            httpSession.setAttribute(ConstantAttributes.MY_PRODUCTS, myProductsForOrders);
            httpSession.setAttribute(ConstantAttributes.MY_ORDERS, myOrders);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MY_ORDERS);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/rateOrder")
    public String rateOrder(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            int orderId = Integer.parseInt(request.getParameter(ConstantAttributes.RATE_ORDER));
            Order rateOrder = orderService.getById(orderId);
            httpSession.setAttribute(ConstantAttributes.RATE_ORDER, rateOrder);
            int formId = formService.createForm();
            request.setAttribute(ConstantParametrs.FORM_ID, formId);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_RATE_ORDER);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/rateOrderForm")
    public String rateOrderForm(HttpServletRequest request) throws ControllerException {
        try {
            int formId = Integer.parseInt(request.getParameter(ConstantParametrs.FORM_ID));
            if (!formService.getById(formId)) {
                HttpSession httpSession = request.getSession();
                Rating rating = Rating.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.RATING_ORDER)));
                Order order = (Order) httpSession.getAttribute(ConstantAttributes.RATE_ORDER);
                order.setRating(rating);
                orderService.updateOrderByAdmin(order);
                formService.update(formId);
                return "redirect " + ConstantURL.MY_ORDERS;
            }
            return "redirect " + ConstantURL.MY_ORDERS;
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

}
