package com.najiujiangzi.jiangzi.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class AdminLoginController {

    @RequestMapping("/home")
    public String home() {
        return "/home/home";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "hello!" + user.getUsername() + ".You are a " + user.getAuthorities().iterator().next();
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login/login";
    }

    @ResponseBody
    @RequestMapping("/notLogin")
    public Map<String, Object> notLogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_FORBIDDEN);
        map.put("message", "未登录");
        return map;
    }

    @ResponseBody
    @RequestMapping("/success")
    public Map<String, Object> success() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_OK);
        map.put("message", "登录成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("/noRights")
    public Map<String, Object> noRights() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_FORBIDDEN);
        map.put("message", "权限不足");
        return map;
    }

    @ResponseBody
    @RequestMapping("/out")
    public Map<String, Object> out() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_OK);
        map.put("message", "退出成功");
        return map;
    }

}
