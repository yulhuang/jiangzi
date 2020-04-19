package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.ImageGroupDTO;
import com.najiujiangzi.jiangzi.model.ImageGroup;
import com.najiujiangzi.jiangzi.service.ImageGroupService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ImageGroupController extends BaseController {

    @Autowired
    private ImageGroupService image_groupService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Map<String, Object> map, Page page) {
        List<ImageGroup> image_groups = image_groupService.find(new ImageGroupDTO(), page);
        map.put("image_groups ", image_groups);
        map.put("page", page);
        return map;
    }
}
