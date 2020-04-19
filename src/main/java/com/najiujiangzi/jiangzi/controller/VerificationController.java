package com.najiujiangzi.jiangzi.controller;

import com.najiujiangzi.jiangzi.dto.VerificationDTO;
import com.najiujiangzi.jiangzi.model.Verification;
import com.najiujiangzi.jiangzi.service.VerificationService;
import com.najiujiangzi.jiangzi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class VerificationController extends BaseController {

    @Autowired
    private VerificationService verificationService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Map<String, Object> map, Page page) {
        List<Verification> verifications = verificationService.find(new VerificationDTO(), page);
        map.put("verifications ", verifications);
        map.put("page", page);
        return map;
    }
}
