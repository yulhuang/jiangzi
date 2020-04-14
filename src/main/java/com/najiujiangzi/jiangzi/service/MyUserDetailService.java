package com.najiujiangzi.jiangzi.service;

import com.alibaba.druid.util.StringUtils;
import com.najiujiangzi.jiangzi.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDTO dto = new UserDTO();
        if (!StringUtils.isEmpty(name) && name.contains("@")) {
            dto.setEmail(name);
        } else if (!StringUtils.isEmpty(name) && name.length() == 11) {
            dto.setPhone(name);
        } else if (!StringUtils.isEmpty(name)) {
            dto.setName(name);
        }
        UserDTO user = userService.findOne(dto);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(roleService.findByUserId(user.getId()));
        return user;
    }

}
