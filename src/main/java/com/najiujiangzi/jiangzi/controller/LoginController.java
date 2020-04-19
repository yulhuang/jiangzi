package com.najiujiangzi.jiangzi.controller;

import com.alibaba.druid.util.StringUtils;
import com.najiujiangzi.jiangzi.enums.GenderType;
import com.najiujiangzi.jiangzi.enums.LoginStatus;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.service.EmailService;
import com.najiujiangzi.jiangzi.service.RoleService;
import com.najiujiangzi.jiangzi.service.UserRoleService;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private EmailService emailService;

//    @ResponseBody
//    @RequestMapping("/test")
//    public String test() {
//        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return "hello!" + user.getUsername() + ".You are a " + user.getAuthorities().iterator().next();
//    }

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

    /**
     * 注册
     *
     * @param map
     * @param email
     * @param name
     * @return
     */
    @ResponseBody
    @Transactional
    @RequestMapping("/register")
    public Map<String, Object> register(Map<String, Object> map, String email, String name) {
        ValidationUtils.assertTrue(!StringUtils.isEmpty(name), "用户名不能为空");
        ValidationUtils.assertTrue(!StringUtils.isEmpty(email), "邮箱不能为空");
        List<User> users = userService.registerByNameOrEmail(name, email);
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                if (user.getName().equals(name)) {
                    map.put("code", LoginStatus.NAMEEXCEPTION.getCode());
                    map.put("message", LoginStatus.NAMEEXCEPTION.getValue());
                    return map;
                } else {
                    map.put("code", LoginStatus.EMAILEXCEPTION.getCode());
                    map.put("message", LoginStatus.EMAILEXCEPTION.getValue());
                    return map;
                }
            }
        }
        String code = "888888";
//        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //邮件发送验证码
        emailService.sendSimpleMail(email, "用户注册验证码", code);

//        userService.createUser(name, email, gender, password);
        return ok();
    }

}
