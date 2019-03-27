package com.gmail.bukato23.util;

import com.gmail.bukato23.util.constant.ConstantURL;

public class ValidURI {
    public  static boolean validURI(String uri){
        return !(uri.equals(ConstantURL.EN) || uri.equals(ConstantURL.RU));
    }
}
