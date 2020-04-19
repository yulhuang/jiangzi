package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailConfig emailConfig;

    public void sendSimpleMail(String to, String subject, String contnet) {
        emailConfig.sendSimpleMail(to, subject, contnet);
    }
}
