package com.najiujiangzi.jiangzi.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String name;
    private String gender;
    private LocalDateTime birthday;
    private String email;
    private String phone;
    private LocalDateTime create;
    private LocalDateTime update;
}
