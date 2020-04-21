package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.EnshrineGroupDTO;
import com.najiujiangzi.jiangzi.model.EnshrineGroup;
import com.najiujiangzi.jiangzi.service.EnshrineGroupService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/enshrineGroup")
public class EnshrineGroupController extends BaseController {

    @Autowired
    private EnshrineGroupService enshrineGroupService;

    @RequestMapping("/list")
    public Map<String, Object> list(Page page) {
        Map<String, Object> map = new HashMap<>();
        List<EnshrineGroup> enshrineGroups = enshrineGroupService.find(new EnshrineGroupDTO(), page);
        map.put("enshrineGroups ", enshrineGroups);
        map.put("page", page);
        return map;
    }
}
