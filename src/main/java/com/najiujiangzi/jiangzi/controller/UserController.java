package com.najiujiangzi.jiangzi.controller;

import com.alibaba.druid.util.StringUtils;
import com.najiujiangzi.jiangzi.config.WebSecurityConfig;
import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PreAuthorize("#oldPassword != null and #newPassword != null ")
    @RequestMapping("/updatePassword")
    public Map<String, Object> updatePassword(String oldPassword, String newPassword) {
        UserDTO user = getUserDTO();
        BCryptPasswordEncoder encoder = WebSecurityConfig.getEncoder();
        if (encoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }
        userService.updatePassword(user);
        return ok();
    }

    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("ok","ok");
        return map;
    }

}
