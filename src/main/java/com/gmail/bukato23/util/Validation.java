package com.gmail.bukato23.util;

import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Validation {
    private static final String CHECK_EMAIL = "[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@" +
            "([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*([a-z]{2,4})";
    private static final String CHECK_PASSWORD = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{8,60}\\z";
    private static final String CHECK_LOGIN = "^[A-z0-9_-]{4,40}$";
    private static final String CHECK_USER_NAME = "^[A-z]{4,50}$";
    private static final String CHECK_AMOUNT = "^[1-9]\\d*$";
    private static final long CHECK_TIME_TWO_HOURS = 7200000;
    private static final long PREODER_TIME_FIVE_HOURS = 18000000;

    public static boolean isCorrectEmail(String email) {
        return email.matches(CHECK_EMAIL);
    }

    public static boolean isCorrectPassword(String password) {
        return password.matches(CHECK_PASSWORD);
    }

    public static boolean isCorrectLogin(String login) {
        return login.matches(CHECK_LOGIN);
    }

    public static boolean isCorrectUserName(String userName) {
        return userName.matches(CHECK_USER_NAME);
    }

    public static boolean isCorrectAccount(BigDecimal account) {
        return account.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean isCorrectPointsLoyalty(int pointsLoyalty) {
        return pointsLoyalty >= 0;
    }

    public static boolean isCorrectReview(String review) {
        return review.length() > 0 && review.length() <= 500;
    }

    public static boolean isCorrectTitle(String title) {
        return title.length() > 0 && title.length() <= 50;
    }

    public static boolean isCorrectURL(String url) {
        String[] schemes = {"http", "https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }
    public static boolean isCorrectAmount(String amount){
        return amount.matches(CHECK_AMOUNT);
    }
    public static boolean isCorrectTimeReciept(Timestamp order, Timestamp receipt){
        return receipt.getTime()-order.getTime() >= CHECK_TIME_TWO_HOURS;
    }
    public static boolean isPreOder(Timestamp timeOrder, Timestamp timeReceipt){
        return timeReceipt.getTime() - timeOrder.getTime() >= PREODER_TIME_FIVE_HOURS;
    }
}
