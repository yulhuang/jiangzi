package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.UserRoleDTO;
import com.najiujiangzi.jiangzi.mappers.UserRoleMapper;
import com.najiujiangzi.jiangzi.model.UserRole;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> find(UserRoleDTO dto, Page page) {
        return userRoleMapper.find(dto, page);
    }

    public Long createCommonUser(Long userId) {
        return userRoleMapper.insertCommonUser(userId);
    }
}
