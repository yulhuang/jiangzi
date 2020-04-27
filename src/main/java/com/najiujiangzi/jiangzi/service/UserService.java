package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.config.WebSecurityConfig;
import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.enums.GenderType;
import com.najiujiangzi.jiangzi.mappers.UserMapper;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;

    public List<User> find(UserDTO dto, Page page) {
        return userMapper.find(dto, page);
    }

    public UserDTO findOne(UserDTO dto) {
        return userMapper.findOne(dto);
    }

    public int insert(UserDTO dto) {
        return userMapper.insert(dto);
    }

    public int update(UserDTO dto) {
        return userMapper.update(dto);
    }

    public List<User> registerByNameOrEmail(String name, String email) {
        return userMapper.registerByNameOrEmail(name, email);
    }

    public int create(User user) {
        return userMapper.insert(user);
    }

    public void createUser(String name, String email, Integer gender, String password) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            String encode = WebSecurityConfig.getEncoder().encode(password);
            User user = new User();
            user.setPhone("15274800577");
            user.setName(name);
            user.setCreate(LocalDateTime.now());
            user.setGender(GenderType.getByCode(gender).getValue());
            user.setEmail(email);
            user.setPassword(encode);
            user.setDeleted(false);
            String account = this.findMaxAccount();
            user.setAccount(account.substring(0, 2) + (Integer.parseInt(account.substring(2)) + 1));
            int i = create(user);
            userRoleService.createCommonUser(user.getId());
        } finally {
            lock.unlock();
        }
    }

    public int updatePassword(UserDTO dto) {
        return userMapper.updatePassword(dto);
    }

    public String findMaxAccount() {
        return userMapper.maxAccount("JZ%");
    }
}
