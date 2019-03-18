package com.gmail.bukato23.controller.impl;

import com.gmail.bukato23.controller.ControllerException;
import com.gmail.bukato23.controller.RequestMappingClass;
import com.gmail.bukato23.controller.RequestMappingMethod;
import com.gmail.bukato23.entity.Product;
import com.gmail.bukato23.entity.ProductCategory;
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
import java.util.List;

@RequestMappingClass(path = "/product")
public class ProductController {
    private ProductService productService = ServiceFactory.getInstance().getProductService();

    @RequestMappingMethod(path = "/allProducts")
    public String showAllProducts(HttpServletRequest request) throws ControllerException {
        try {
            List<Product> products = productService.getAll();
            request.setAttribute("products", products);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ALL_PRODUCTS);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/addProduct")
    public String addProduct(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ADD_PRODUCT);
    }

    @RequestMappingMethod(path = "/addProductForm")
    public String addProductForm(HttpServletRequest request) throws ControllerException {
        try {
            String title = request.getParameter(ConstantParametrs.TITLE);
            String description = request.getParameter(ConstantParametrs.DESCRIPTION);
            String picture = request.getParameter(ConstantParametrs.PICTURE);
            BigDecimal price = BigDecimal.valueOf(Float.parseFloat(request.getParameter(ConstantParametrs.PRICE)));
            ProductCategory productCategory = ProductCategory.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.CATEGORY_PRODUCT)));
            Boolean availabilityProduct = Boolean.parseBoolean(request.getParameter(ConstantParametrs.AVAILABILITY_PRODUCT));

            HttpSession httpSession = request.getSession();
            request.setAttribute(ConstantAttributes.ERROR_WRONG_TITLE, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_DESCRIPTION, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_IMAGE, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PRICE, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));
            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ADD_PRODUCT);
            if (Validation.isCorrectTitle(title)) {
                if (Validation.isCorrectReview(description)) {
                    if (Validation.isCorrectURL(picture)) {
                        if (Validation.isCorrectAccount(price)) {
                            Product product = new Product();
                            product.setTitle(title);
                            product.setDescription(description);
                            product.setPicture(picture);
                            product.setPrice(price);
                            product.setCategory(productCategory);
                            product.setAvailability(availabilityProduct);
                            productService.addProduct(product);
                            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_PROFILE);
                        } else {
                            request.setAttribute(ConstantAttributes.ERROR_WRONG_PRICE, messageManager.
                                    getMessage(ConstantMessages.PATH_ERROR_WRONG_PRICE));
                        }
                    } else {
                        request.setAttribute(ConstantAttributes.ERROR_WRONG_IMAGE, messageManager.
                                getMessage(ConstantMessages.PATH_ERROR_WRONG_IMAGE));
                    }
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_DESCRIPTION, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_DESCRIPTION));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_TITLE, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_TITLE));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/editProduct")
    public String editProduct(HttpServletRequest request) throws ControllerException {
        try {
            int productId = Integer.parseInt(request.getParameter(ConstantAttributes.EDIT_PRODUCT));
            Product editProduct = productService.getByID(productId);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.EDIT_PRODUCT, editProduct);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_PRODUCT);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/editProductForm")
    public String editProductForm(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            Product product = (Product) httpSession.getAttribute(ConstantAttributes.EDIT_PRODUCT);
            String title = request.getParameter(ConstantParametrs.TITLE);
            if (title.isEmpty()) {
                title = product.getTitle();
            }
            String description = request.getParameter(ConstantParametrs.DESCRIPTION);
            if (description.isEmpty()) {
                description = product.getDescription();
            }
            String picture = request.getParameter(ConstantParametrs.PICTURE);
            if (picture.isEmpty()) {
                picture = product.getPicture();
            }
            BigDecimal price = BigDecimal.valueOf(Float.parseFloat(request.getParameter(ConstantParametrs.PRICE)));
            ProductCategory productCategory = ProductCategory.fromID(Integer.parseInt(request.getParameter(ConstantParametrs.CATEGORY_PRODUCT)));
            Boolean availabilityProduct = Boolean.parseBoolean(request.getParameter(ConstantParametrs.AVAILABILITY_PRODUCT));

            request.setAttribute(ConstantAttributes.ERROR_WRONG_TITLE, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_DESCRIPTION, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_IMAGE, null);
            request.setAttribute(ConstantAttributes.ERROR_WRONG_PRICE, null);

            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));
            String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_EDIT_PRODUCT);
            if (Validation.isCorrectTitle(title)) {
                if (Validation.isCorrectReview(description)) {
                    if (Validation.isCorrectURL(picture)) {
                        if (Validation.isCorrectAccount(price)) {
                            product.setTitle(title);
                            product.setDescription(description);
                            product.setPicture(picture);
                            product.setPrice(price);
                            product.setCategory(productCategory);
                            product.setAvailability(availabilityProduct);
                            productService.updateProductByAdmin(product);
                            page = ConstantURL.ALL_PRODUCTS;
                        } else {
                            request.setAttribute(ConstantAttributes.ERROR_WRONG_PRICE, messageManager.
                                    getMessage(ConstantMessages.PATH_ERROR_WRONG_PRICE));
                        }
                    } else {
                        request.setAttribute(ConstantAttributes.ERROR_WRONG_IMAGE, messageManager.
                                getMessage(ConstantMessages.PATH_ERROR_WRONG_IMAGE));
                    }
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_DESCRIPTION, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_DESCRIPTION));
                }
            } else {
                request.setAttribute(ConstantAttributes.ERROR_WRONG_TITLE, messageManager.
                        getMessage(ConstantMessages.PATH_ERROR_WRONG_TITLE));
            }
            return page;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/deleteProduct")
    public String deleteProduct(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            Product product = (Product) httpSession.getAttribute(ConstantAttributes.EDIT_PRODUCT);
            productService.deleteProduct(product);
            return ConstantURL.ALL_PRODUCTS;
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    @RequestMappingMethod(path = "/menu")
    public String showMenu(HttpServletRequest request) throws ControllerException {
        try {
            List<Product> products = productService.getAll();
            request.setAttribute("products", products);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MENU);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }
}
