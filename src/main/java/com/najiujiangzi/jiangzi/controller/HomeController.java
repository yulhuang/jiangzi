package com.najiujiangzi.jiangzi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @ResponseBody
    @RequestMapping("/images")
    public Map<String, Object> images(Map<String, Object> map) {

        return map;
    }
}
