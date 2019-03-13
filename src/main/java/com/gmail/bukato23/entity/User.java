package com.gmail.bukato23.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class User implements Identified<Integer> {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal account;
    private int pointsLoyalty;
    private boolean blocked;
    private Date registrationDate;
    private UserRole role = UserRole.USER;

    public User(String login, String password, String firstName, String lastName, String email, BigDecimal account, int pointsLoyalty, boolean blocked, Date registrationDate, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.account = account;
        this.pointsLoyalty = pointsLoyalty;
        this.blocked = blocked;
        this.registrationDate = registrationDate;
    }

    public User() {
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
