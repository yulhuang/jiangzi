package com.najiujiangzi.jiangzi.util;

public class ValidationUtils {
    public static void assertTrue(boolean value, String msg) {
        if (value) {
            throw new RuntimeException(msg);
        }
    }
}
