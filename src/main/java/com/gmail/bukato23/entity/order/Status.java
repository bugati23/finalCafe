package com.gmail.bukato23.entity.order;


import com.gmail.bukato23.entity.Identified;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Status implements Identified<Integer> {
    EXPECTS(1), CLOSED(2), EXPIRED(3);
    private int id;

    Status(int id) {
        this.id = id;
    }

    static final Map<Integer, Status> values = Arrays.stream(Status.values())
            .collect(Collectors.toMap(Status::getId, Function.identity()));

    public static Status fromID(final int value) {
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
