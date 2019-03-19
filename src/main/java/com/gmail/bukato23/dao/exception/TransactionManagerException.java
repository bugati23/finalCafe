package com.gmail.bukato23.dao.exception;

public class TransactionManagerException extends RuntimeException {
    public TransactionManagerException() {
    }

    public TransactionManagerException(String message) {
        super(message);
    }

    public TransactionManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionManagerException(Throwable cause) {
        super(cause);
    }

    public TransactionManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
