package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

}
