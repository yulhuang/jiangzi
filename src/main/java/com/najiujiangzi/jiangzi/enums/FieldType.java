package com.najiujiangzi.jiangzi.enums;

public enum FieldType {
    STRING("String", "varchar"),
    STRING2("String", "char"),
    STRING3("String", "text"),
    BYTE("byte[]", "blob"),
    LONG("Long", "integer"),
    LONG2("Long", "bigint"),
    BOOLEAN("Boolean", "tinyint"),
    INTERGER("Integer", "int"),
    DATETIME("LocalDateTime", "datetime");


    private String fieldToJava;
    private String fieldToMysql;

    FieldType(String fieldToJava, String fieldToMysql) {
        this.fieldToJava = fieldToJava;
        this.fieldToMysql = fieldToMysql;
    }

    public static FieldType getByMysql(String mysqlType) {
        for (FieldType value : FieldType.values()) {
            if (value.getFieldToMysql().equals(mysqlType)) {
                return value;
            }
        }
        return null;
    }

    public String getFieldToJava() {
        return fieldToJava;
    }

    public String getFieldToMysql() {
        return fieldToMysql;
    }
}
