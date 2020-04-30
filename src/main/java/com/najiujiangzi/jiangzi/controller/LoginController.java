package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.annotation.Null;
import com.najiujiangzi.jiangzi.enums.LoginStatus;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.util.NumberOfUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Value("${isServer}")
    protected Boolean isServer;

    @Autowired
    private UserService userService;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login/login.html";
    }

    @ResponseBody
    @RequestMapping("/notLogin")
    public Map<String, Object> notLogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", HttpServletResponse.SC_FORBIDDEN);
        map.put("success", false);
        map.put("message", "未登录");
        return map;
    }

    @ResponseBody
    @RequestMapping("/success")
    public Map<String, Object> success() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", HttpServletResponse.SC_OK);
        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("/noRights")
    public Map<String, Object> noRights() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", HttpServletResponse.SC_FORBIDDEN);
        map.put("success", false);
        map.put("message", "权限不足");
        return map;
    }

    @ResponseBody
    @RequestMapping("/out")
    public Map<String, Object> out() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", HttpServletResponse.SC_OK);
        map.put("success", true);
        map.put("message", "退出成功");
        return map;
    }

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @param name
     * @return
     */
    @Null
    @ResponseBody
    @Transactional
    @RequestMapping("/register")
    public Map<String, Object> register(String email, String name) {
        List<User> users = userService.registerByNameOrEmail(name, email);
        if (!CollectionUtils.isEmpty(users)) {
            Map<String, Object> map = new HashMap<>();
            for (User user : users) {
                map.put("success", false);
                if (user.getName().equals(name)) {
                    map.put("statusCode", LoginStatus.NAMEEXCEPTION.getCode());
                    map.put("message", LoginStatus.NAMEEXCEPTION.getValue());
                    return map;
                } else {
                    map.put("statusCode", LoginStatus.EMAILEXCEPTION.getCode());
                    map.put("message", LoginStatus.EMAILEXCEPTION.getValue());
                    return map;
                }
            }
        }
        String code;
        if (isServer) {
            code = (int) ((Math.random() * 9 + 1) * 100000) + "";
            redisUtil.setex("emailCode_" + email, (int) TimeUnit.SECONDS.convert(5, TimeUnit.MINUTES), code);
            //邮件发送验证码
            try {
                producer.emailAsyncProducer(email, code);
            } catch (Exception e) {
                throw new RuntimeException("验证码发送失败！");
            }
        }
        return ok();
    }

    /**
     * 注册添加用户
     *
     * @param name
     * @param email
     * @param gender
     * @param password
     * @param code
     * @return
     */
    @Null
    @ResponseBody
    @Transactional
    @RequestMapping("/saveUser")
    public Map<String, Object> saveUser(String name, String email, Integer gender, String password, String code) {
        if (isServer) {
            String emailCode = redisUtil.get("emailCode_" + email);
            if (emailCode == null) {
                Map<String, Object> map = new HashMap<>();
                map.put("statusCode", false);
                map.put("message", "验证码失效");
                return map;
            } else if (!emailCode.equals(code)) {
                Map<String, Object> map = new HashMap<>();
                map.put("statusCode", false);
                map.put("message", "验证码错误");
                return map;
            }
        } else {
            if (!code.equals("888888")) {
                Map<String, Object> map = new HashMap<>();
                map.put("statusCode", false);
                map.put("message", "验证码错误");
                return map;
            }
        }
        userService.createUser(name, email, gender, password);
        redisUtil.del("emailCode_" + email);
        //日志统计今日新增用户
        redisUtil.incrby(NumberOfUser.getNewUserKey(), 1L);
        log.info(LocalDateTime.now() + "新增用户-->" + email + "; 今日共增-->" + redisUtil.get(NumberOfUser.getNewUserKey()));
        return ok();
    }

    @ResponseBody
    @RequestMapping("/testEmail")
    public String testEmail() {
        try {
            producer.emailAsyncProducer("297304818@qq.com", "888888");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "email_ok";
    }

}
