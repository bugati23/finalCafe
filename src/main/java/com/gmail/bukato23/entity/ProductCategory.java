package com.gmail.bukato23.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProductCategory implements Identified<Integer> {
    FIRST_COURSE(1), MAIN_COURSE(2), DRINK(3);
    private int id;

    ProductCategory(int id) {
        this.id = id;
    }

    static final Map<Integer, ProductCategory> values = Arrays.stream(ProductCategory.values())
            .collect(Collectors.toMap(ProductCategory::getId, Function.identity()));

    public static ProductCategory fromID(final int value) {
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
