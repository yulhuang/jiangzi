package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.EnshrineImageDTO;
import com.najiujiangzi.jiangzi.model.EnshrineImage;
import com.najiujiangzi.jiangzi.service.EnshrineImageService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/enshrineImage")
public class EnshrineImageController extends BaseController {

    @Autowired
    private EnshrineImageService enshrineImageService;

    @RequestMapping("/list")
    public Map<String, Object> list(Page page) {
        Map<String, Object> map = new HashMap<>();
        List<EnshrineImage> enshrineImages = enshrineImageService.find(new EnshrineImageDTO(), page);
        map.put("enshrineImages ", enshrineImages);
        map.put("page", page);
        return map;
    }
}
