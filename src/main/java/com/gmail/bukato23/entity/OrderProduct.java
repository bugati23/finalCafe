package com.gmail.bukato23.entity;

import lombok.Data;

@Data
public class OrderProduct implements Identified<Integer> {
    private int id;
    private int orderId;
    private int productId;
    private int amount;
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    public OrderProduct() {
    }

    public OrderProduct(int id, int orderId, int productId,int amount) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }
}
