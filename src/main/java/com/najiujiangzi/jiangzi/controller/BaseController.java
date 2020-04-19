package com.najiujiangzi.jiangzi.controller;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_OK);
        map.put("msg", "操作成功");
        return map;
    }
}
