package com.gmail.bukato23.entity.order;


import com.gmail.bukato23.entity.Identified;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order implements Identified<Integer> {
    private int id;
    private int userId;
    private PaymentType paymentType;
    private Rating rating;
    private Status status;
    private BigDecimal totalAmount;
    private boolean preOder;
    private Timestamp dateOrder;
    private Timestamp dateReceipt;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(int userId, PaymentType paymentType, Rating rating, Status status, BigDecimal totalAmount, boolean preOder, Timestamp dateOrder, Timestamp dateReceipt) {
        this.userId = userId;
        this.paymentType = paymentType;
        this.rating = rating;
        this.status = status;
        this.totalAmount = totalAmount;
        this.preOder = preOder;
        this.dateOrder = dateOrder;
        this.dateReceipt = dateReceipt;
    }
}
