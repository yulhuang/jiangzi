package com.najiujiangzi.jiangzi.service;

import com.najiujiangzi.jiangzi.config.EmailConfig;
import com.najiujiangzi.jiangzi.enums.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {
    protected final static Logger logger = LoggerFactory.getLogger(EmailService.class);

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
     * 发送邮件验证码
     *
     * @param to
     * @param subject
     * @param code
     */
    public void sendVerification(String to, String subject, String code) {
        logger.info("发送验证码" + code + "到" + to);
        try {
            emailConfig.sendHtmlMail(to, subject, EmailTemplate.VERIFICATION.getValue().replaceAll("@verification", code));
        } catch (MessagingException e) {
            logger.error("发送邮箱报错： {},{}", e.getMessage(), e);
        }
    }
}
