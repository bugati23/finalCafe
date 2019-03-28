package com.gmail.bukato23.controller.impl;

import com.gmail.bukato23.controller.ControllerException;
import com.gmail.bukato23.controller.RequestMappingClass;
import com.gmail.bukato23.controller.RequestMappingMethod;
import com.gmail.bukato23.entity.Feedback;
import com.gmail.bukato23.entity.User;
import com.gmail.bukato23.service.*;
import com.gmail.bukato23.util.Validation;
import com.gmail.bukato23.util.constant.*;
import com.gmail.bukato23.util.property.ConfigurationManager;
import com.gmail.bukato23.util.property.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMappingClass(path = "/feedback")
public class FeedbackController {
    private FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
    private FormService formService = ServiceFactory.getInstance().getFormService();

    @RequestMappingMethod(path = "/reviews")
    public String reviews(HttpServletRequest request) throws ControllerException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.ALL_FEEDBACKS);
            List<User> userFeedback = new ArrayList<>();
            List<Feedback> reviews = feedbackService.getAll();
            for (Feedback feedback : reviews) {
                User user = userService.getByID(feedback.getUserId());
                userFeedback.add(user);
            }
            request.setAttribute("reviews", reviews);
            request.setAttribute("userFeedback", userFeedback);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_REVIEWS);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/addReview")
    public String addreview(HttpServletRequest request) throws ControllerException {
        try {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(ConstantAttributes.CURRENT_GET_PAGE, ConstantURL.ADD_REVIEW);
            int formId = formService.createForm();
            request.setAttribute(ConstantParametrs.FORM_ID, formId);
            return ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ADD_REVIEW);
        } catch (ServiceException exc) {
            throw new ControllerException(exc);
        }
    }

    @RequestMappingMethod(path = "/addReviewForm")
    public String addReviewForm(HttpServletRequest request) throws ControllerException {
        try {
            int formId = Integer.parseInt(request.getParameter(ConstantParametrs.FORM_ID));
            if (!formService.getById(formId)) {
                String review = request.getParameter(ConstantParametrs.REVIEW);
                HttpSession httpSession = request.getSession();
                request.setAttribute(ConstantAttributes.ERROR_WRONG_REVIEW, null);
                MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                        ConstantAttributes.CHANGE_LANGUAGE));
                String page = "redirect " + ConstantURL.ADD_REVIEW;
                if (Validation.isCorrectReview(review)) {
                    User user = (User) httpSession.getAttribute(ConstantAttributes.USER);
                    Feedback feedback = new Feedback();
                    feedback.setReview(review);
                    feedback.setUserId(user.getId());
                    feedbackService.addFeedback(feedback);
                    formService.update(formId);
                    page = "redirect " + ConstantURL.ALL_FEEDBACKS;
                } else {
                    request.setAttribute(ConstantAttributes.ERROR_WRONG_REVIEW, messageManager.
                            getMessage(ConstantMessages.PATH_ERROR_WRONG_REVIEW));
                }
                return page;
            }
            return "redirect " + ConstantURL.ALL_FEEDBACKS;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
