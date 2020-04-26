package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.service.EmailService;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationService verificationService;

    protected Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_OK);
        map.put("success", true);
        map.put("msg", "操作成功");
        return map;
    }

}
