package com.gmail.bukato23.entity;

import lombok.Data;

@Data
public class Form implements Identified<Integer> {
    private int id;
    private boolean fulfilled;
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
