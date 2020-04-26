package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.config.EmailConfig;
import com.najiujiangzi.jiangzi.enums.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private EmailConfig emailConfig;

    /**
     * 发送普通邮件
     *
     * @param to
     * @param subject
     * @param contnet
     */
    public void sendSimpleMail(String to, String subject, String contnet) {
        emailConfig.sendSimpleMail(to, subject, contnet);
    }

    /**
     * 发送http邮件验证码
     *
     * @param to
     * @param subject
     * @param code
     */
    public void sendVerification(String to, String subject, String code) {
        log.info("发送验证码" + code + "到" + to);
        try {
            emailConfig.sendHtmlMail(to, subject, EmailTemplate.VERIFICATION.getValue().replaceAll("@verification", code));
        } catch (MessagingException e) {
            log.error("发送邮箱报错： {},{}", e.getMessage(), e);
        }
    }
}
