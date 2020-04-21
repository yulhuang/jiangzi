package com.najiujiangzi.jiangzi.controller;

import com.alibaba.druid.util.StringUtils;
import com.najiujiangzi.jiangzi.dto.VerificationDTO;
import com.najiujiangzi.jiangzi.enums.GenderType;
import com.najiujiangzi.jiangzi.enums.LoginStatus;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.service.EmailService;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.service.VerificationService;
import com.najiujiangzi.jiangzi.util.NumberOfUser;
import com.najiujiangzi.jiangzi.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


//@RestController
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    protected final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${isServer}")
    protected Boolean isServer;

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationService verificationService;


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
    @ResponseBody
    @Transactional
    @RequestMapping("/register")
    public Map<String, Object> register(String email, String name) {
        ValidationUtils.assertTrue(StringUtils.isEmpty(name), "用户名不能为空");
        ValidationUtils.assertTrue(StringUtils.isEmpty(email), "邮箱不能为空");
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.registerByNameOrEmail(name, email);
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                if (user.getName().equals(name)) {
                    map.put("statusCode", LoginStatus.NAMEEXCEPTION.getCode());
                    map.put("success", false);
                    map.put("message", LoginStatus.NAMEEXCEPTION.getValue());
                    return map;
                } else {
                    map.put("statusCode", LoginStatus.EMAILEXCEPTION.getCode());
                    map.put("success", false);
                    map.put("message", LoginStatus.EMAILEXCEPTION.getValue());
                    return map;
                }
            }
        }
        String code;
        if (isServer) {
            code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        } else {
            code = "888888";
        }
        VerificationDTO dto = new VerificationDTO();
        dto.setCode(code);
        dto.setCreate(LocalDateTime.now());
        dto.setEmail(email);
        verificationService.insert(dto);
        //邮件发送验证码
        emailService.sendVerification(email, "验证码", code);
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
    @ResponseBody
    @RequestMapping("/saveUser")
    public Map<String, Object> saveUser(String name, String email, Integer gender, String password, String code) {
        ValidationUtils.assertTrue(StringUtils.isEmpty(name), "用户名不能为空");
        ValidationUtils.assertTrue(StringUtils.isEmpty(email), "邮箱不能为空");
        ValidationUtils.assertTrue(gender == null && GenderType.getByCode(gender) == null, "性别不能为空");
        ValidationUtils.assertTrue(StringUtils.isEmpty(password), "密码不能为空");
        ValidationUtils.assertTrue(StringUtils.isEmpty(code), "验证码不能为空");
        VerificationDTO byCodeAndCreate = verificationService.findByCodeAndEmail(code, email);
        if (byCodeAndCreate == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", false);
            map.put("message", "验证码错误或失效");
            return map;
        }
        userService.createUser(name, email, gender, password);
        verificationService.deleteById(byCodeAndCreate.getId());

        logger.info("今天新增用户" + NumberOfUser.addTodayCreateUser());
        return ok();
    }

}
