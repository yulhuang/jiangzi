package com.najiujiangzi.jiangzi.enums;

public enum EmailTemplate {
    /**
     * 邮箱验证码模板
     */
    VERIFICATION("<td valign=\"top\"><table align=\"center\" bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"full-width\" style=\"background-color:#ffffff; width:600px;\" width=\"600\">" +
            "<tbody><tr><td style=\"vertical-align:top;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"full-width\" style=\"width:600px;\" width=\"600\">" +
            "<tbody><tr><td align=\"right\" class=\"logo\" style=\"color:#ffffff; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:18px; padding-bottom:12px; text-align:right; width:588px;\" valign=\"top\" width=\"588\">" +
            //logo图片
            "<img border=\"0\" class=\"logo\" height=\"64\" hspace=\"0\" src=\"http://landing.adobe.com/dam/global/images/adobe-logo.red-tag.78x128.png\" style=\"background-color:#ff0000; display:inline-block; vertical-align:top;\" vspace=\"0\" width=\"39\"></td>" +
            "<td style=\"width:12px;\" width=\"12\">&nbsp;</td></tr></tbody></table></td></tr><tr><td valign=\"top\"><table align=\"center\" bgcolor=\"#ffffff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"full-width\" style=\"background-color:#ffffff; width:600px;\" width=\"600\">" +
            "<tbody><tr><td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td><td style=\"color:#666666; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:18px;\">您的验证码为：</td>" +
            "<td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td></tr><tr><td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td>" +
            "<td style=\"color:#666666; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:18px; padding-top:12px;\">" +
            //验证码
            "<strong>@verification</strong></td>" +
            "<td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td></tr><tr><td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td>" +
            "<td style=\"color:#666666; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:18px; padding-top:12px;\">如果您并未请求进行此操作，" +
            //联系我们链接
            "<a href=\"https://helpx.adobe.com/cn/contact/support.html\" style=\"color:#2b9af3; text-decoration:none;\" target=\"_blank\" rel=\"noopener\">请立即与我们联系</a>。</td><td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td>" +
            "</tr><tr><td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td><td style=\"color:#666666; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:18px; padding-top:12px; padding-bottom:40px;\">谢谢,<br> Adobe</td>" +
            "<td class=\"mobile-spacer\" style=\"width:30px;\" width=\"30\">&nbsp;</td></tr></tbody></table></td></tr><tr><td valign=\"top\"><table align=\"center\" bgcolor=\"#f0f0f0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"full-width\" style=\"background-color:#f0f0f0; width:600px;\" width=\"600\">" +
            "<tbody><tr><td style=\"width:22px;\" width=\"22\">&nbsp;</td>" +
            "<td align=\"left\" style=\"color:#a1a1a1; font-family:Arial, Helvetica, sans-serif; font-size:11px; line-height:14px; padding-top:30px; padding-bottom:30px; text-align:left;\">" +
            //版权信息
            "Adobe and the Adobe logo are either registered trademarks or trademarks of Adobe Systems Incorporated in the United States and/or other countries. All other trademarks are the property of their respective owners." +
            "<br><br> © 2020 Adobe Systems Incorporated. All rights reserved.<br><br> <span class=\"appleLinksFooter\"> " +
            "注册办公室：Adobe Systems Software Ireland Limited, <span style=\"border-bottom:1px dashed #ccc;\" t=\"5\" times=\"\">4-6</span> Riverwalk, Citywest Business Park, Dublin 24, Ireland. 注册编号：344992</span></td>" +
            "<td style=\"width:22px;\" width=\"22\">&nbsp;</td>" +
            "</tr></tbody></table></td></tr></tbody></table></td>");


    private String value;

    EmailTemplate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
