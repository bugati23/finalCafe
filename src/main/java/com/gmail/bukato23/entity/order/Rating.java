package com.gmail.bukato23.entity.order;


import com.gmail.bukato23.entity.Identified;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Rating implements Identified<Integer> {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),  DEFAULT(6);
    private int id;

    Rating(int id) {
        this.id = id;
    }

    static final Map<Integer, Rating> values = Arrays.stream(Rating.values())
            .collect(Collectors.toMap(Rating::getId, Function.identity()));

    public static Rating fromID(final int value) {
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
