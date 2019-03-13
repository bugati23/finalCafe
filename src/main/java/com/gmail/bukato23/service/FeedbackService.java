package com.gmail.bukato23.service;

import com.gmail.bukato23.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    void addFeedback(Feedback feedback) throws ServiceException;

    List<Feedback> getAll() throws ServiceException;
}
