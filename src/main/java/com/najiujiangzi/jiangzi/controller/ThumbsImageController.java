package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.ThumbsImageDTO;
import com.najiujiangzi.jiangzi.model.ThumbsImage;
import com.najiujiangzi.jiangzi.service.ThumbsImageService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/thumbsImage")
public class ThumbsImageController extends BaseController {

    @Autowired
    private ThumbsImageService thumbsImageService;

    @RequestMapping("/list")
    public Map<String, Object> list(Page page) {
        Map<String, Object> map = new HashMap<>();
        List<ThumbsImage> thumbsImages = thumbsImageService.find(new ThumbsImageDTO(), page);
        map.put("thumbsImages ", thumbsImages);
        map.put("page", page);
        return map;
    }
}
