package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.dto.RoleDTO;
import com.najiujiangzi.jiangzi.mappers.RoleMapper;
import com.najiujiangzi.jiangzi.model.Role;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    List<Role> find(RoleDTO dto, Page page) {
        return roleMapper.find(dto, page);
    }
}
