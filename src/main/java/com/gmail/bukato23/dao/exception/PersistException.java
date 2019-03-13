package com.gmail.bukato23.dao.exception;

/**
 * Persist Exception
 */
public class PersistException extends Exception {

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }
    public PersistException(String message) {
        super(message);
    }//provide your code here

}
