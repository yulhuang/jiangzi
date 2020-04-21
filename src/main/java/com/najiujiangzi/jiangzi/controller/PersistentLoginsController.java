package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.PersistentLoginsDTO;
import com.najiujiangzi.jiangzi.model.PersistentLogins;
import com.najiujiangzi.jiangzi.service.PersistentLoginsService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/persistentLogins")
public class PersistentLoginsController extends BaseController {

    @Autowired
    private PersistentLoginsService persistentLoginsService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Page page) {
        Map<String, Object> map = new HashMap<>();
        List<PersistentLogins> persistentLoginss = persistentLoginsService.find(new PersistentLoginsDTO(), page);
        map.put("persistentLoginss ", persistentLoginss);
        map.put("page", page);
        return map;
    }
}
