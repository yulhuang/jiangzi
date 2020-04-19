package com.najiujiangzi.jiangzi.enums;

public enum  GenderType {
    MAN(1, "男"),
    WUMAN(2, "女");

    private int code;
    private String value;

    GenderType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static GenderType getByCode(int code) {
        for (GenderType loginStatus : GenderType.values()) {
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
