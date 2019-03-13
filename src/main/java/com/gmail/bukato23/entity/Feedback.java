package com.gmail.bukato23.entity;

import lombok.Data;

@Data
public class Feedback implements Identified<Integer> {
    private int id;
    private int userId;
    private String review;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Feedback(int userId, String review) {
        this.userId = userId;
        this.review = review;
    }

    public Feedback() {
    }
}
