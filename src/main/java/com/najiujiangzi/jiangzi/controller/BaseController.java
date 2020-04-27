package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.rocketMQ.Consumer;
import com.najiujiangzi.jiangzi.rocketMQ.Producer;
import com.najiujiangzi.jiangzi.service.EmailService;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    @Autowired
    protected UserService userService;
    @Autowired
    protected EmailService emailService;
    @Autowired
    protected Producer producer;
    @Autowired
    protected Consumer consumer;
    @Autowired
    protected RedisUtil redisUtil;

    Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_OK);
        map.put("success", true);
        map.put("msg", "操作成功");
        return map;
    }

    UserDTO getUserDTO() {
        final UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    Long getUserId() {
        return this.getUserDTO().getId();
    }

}
