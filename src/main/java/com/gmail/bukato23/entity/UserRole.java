package com.gmail.bukato23.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserRole implements Identified<Integer> {
    USER(2), ADMIN(1);
    private int id;

    UserRole(int id) {
        this.id = id;
    }

    static final Map<Integer, UserRole> values = Arrays.stream(UserRole.values())
            .collect(Collectors.toMap(UserRole::getId, Function.identity()));

    public static UserRole fromID(final int value) {
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
