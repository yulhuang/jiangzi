package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.ImageDTO;
import com.najiujiangzi.jiangzi.model.Image;
import com.najiujiangzi.jiangzi.service.ImageService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ImageController extends BaseController {

    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Map<String, Object> map, Page page) {
        List<Image> images = imageService.find(new ImageDTO(), page);
        map.put("images ", images);
        map.put("page", page);
        return map;
    }
}
