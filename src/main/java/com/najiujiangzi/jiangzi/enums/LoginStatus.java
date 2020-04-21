package com.najiujiangzi.jiangzi.enums;

import org.apache.ibatis.annotations.Insert;

public enum LoginStatus {
    NAMEEXCEPTION(1, "用户名已存在"),
    EMAILEXCEPTION(2, "邮箱已存在，是否忘记密码");
    private int code;
    private String value;

    LoginStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static LoginStatus getByCode(int code) {
        for (LoginStatus loginStatus : LoginStatus.values()) {
            if (loginStatus.getCode() == code) {
                return loginStatus;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
