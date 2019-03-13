package com.gmail.bukato23.entity.order;


import com.gmail.bukato23.entity.Identified;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentType implements Identified<Integer> {
    CASH(1), ONLINE_ACCOUNT(2);
    private int id;

    PaymentType(int id) {
        this.id = id;
    }

    static final Map<Integer, PaymentType> values = Arrays.stream(PaymentType.values())
            .collect(Collectors.toMap(PaymentType::getId, Function.identity()));

    public static PaymentType fromID(final int value) {
        return values.get(value);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
