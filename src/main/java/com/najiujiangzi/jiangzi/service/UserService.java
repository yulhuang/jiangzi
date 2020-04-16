package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.mappers.UserMapper;
import com.najiujiangzi.jiangzi.model.User;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> find(UserDTO dto, Page page) {
        return userMapper.find(dto, page);
    }

    public UserDTO findOne(UserDTO dto) {
        return userMapper.findOne(dto);
    }

    public Long insert(UserDTO dto) {
        return userMapper.insert(dto);
    }

    public Long update(UserDTO dto) {
        return userMapper.update(dto);
    }
}
