package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.RoleDTO;
import com.najiujiangzi.jiangzi.model.Role;
import com.najiujiangzi.jiangzi.service.RoleService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Page page) {
        Map<String, Object> map = new HashMap<>();
        List<Role> roles = roleService.find(new RoleDTO(), page);
        map.put("roles ", roles);
        map.put("page", page);
        return map;
    }
}
