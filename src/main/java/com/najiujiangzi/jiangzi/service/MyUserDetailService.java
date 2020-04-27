package com.najiujiangzi.jiangzi.service;

import com.alibaba.druid.util.StringUtils;
import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.util.NumberOfUser;
import com.najiujiangzi.jiangzi.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    protected final static Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserDTO dto = new UserDTO();
        if (!StringUtils.isEmpty(account) && account.contains("@")) {
            dto.setEmail(account);
        } else /*if (!StringUtils.isEmpty(name) && name.length() == 11)*/ {
            dto.setAccount(account.toUpperCase());
        }
        UserDTO user = userService.findOne(dto);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(roleService.findByUserId(user.getId()));
        //日志统计今日登录人数
        redisUtil.incrby(NumberOfUser.getLoginUserKey(), 1L);
        logger.info("登录账号-->" + account + "; 今日总登录数-->" + redisUtil.get(NumberOfUser.getLoginUserKey()));
        return user;
    }
}
