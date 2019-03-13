package com.gmail.bukato23.dao;

import java.sql.Connection;
import java.util.List;

public class Transaction {
    private Connection proxyConnection;
    private boolean readOnly;

    public Transaction(Connection proxyConnection, boolean readOnly) {
        this.proxyConnection = proxyConnection;
    }

    public Transaction(Connection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public void setProxyConnection(Connection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
