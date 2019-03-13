package com.gmail.bukato23.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product implements Identified<Integer> {
    private int id;
    private String title;
    private String description;
    private String picture;
    private BigDecimal price;
    private boolean availability;
    private ProductCategory category;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Product() {
    }

    public Product(String title, String description, String picture, BigDecimal price, boolean availability, ProductCategory category) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.availability = availability;
        this.category = category;
    }
}
