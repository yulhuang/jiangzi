package com.najiujiangzi.jiangzi.dto;

public class EmailAndCodeDTO {
    private String email;
    private String subject;
    private String code;

    public EmailAndCodeDTO() {
    }

    public EmailAndCodeDTO(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public EmailAndCodeDTO(String email, String subject, String code) {
        this.email = email;
        this.subject = subject;
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return email +
                "," + subject +
                "," + code;
    }
}
