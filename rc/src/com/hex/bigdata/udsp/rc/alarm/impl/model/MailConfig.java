package com.hex.bigdata.udsp.rc.alarm.impl.model;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.rc.alarm.model.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 邮箱警报的配置参数
 */
public class MailConfig extends Config {

    public MailConfig(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    /**
     * SMTP服务器地址
     *
     * @return
     */
    public String gainMailSmtpHost() {
        String value = gainProperty("mail.smtp.host").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("mail.smtp.host不能为空");
        }
        return value;
    }

    /**
     * SMTP服务器端口
     *
     * @return
     */
    public int gainMailSmtpPort() {
        String value = gainProperty("mail.smtp.port").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value)) {
            throw new IllegalArgumentException ("mail.smtp.port不能为空且必须是整数");
        }
        return Integer.valueOf(value);
    }

    /**
     * 发件人
     *
     * @return
     */
    public String gainMailSmtpFrom() {
        String value = gainProperty("mail.smtp.from").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("mail.smtp.from不能为空");
        }
        return value;
    }

    /**
     * 用户名
     *
     * @return
     */
    public String gainMailSmtpUsername() {
        return gainProperty("mail.smtp.username").getValue();
    }

    /**
     * 密码
     *
     * @return
     */
    public String gainMailSmtpPassword() {
        return gainProperty("mail.smtp.password").getValue();
    }

    /**
     * 是否检查服务器的身份
     *
     * @return
     */
    public boolean gainMailSmtpSslCheckServerIdentity() {
        String value = gainProperty("mail.smtp.ssl.check.server.identity").getValue();
        if (StringUtils.isBlank(value)) {value = "false";}
        return Boolean.valueOf(value);
    }

    /**
     * 收件人
     *
     * @return
     */
    public String gainMailSmtpTo() {
        String value = gainProperty("mail.smtp.to").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("mail.smtp.to不能为空");
        }
        return value;
    }

    /**
     * 抄送
     *
     * @return
     */
    public String gainMailSmtpCc() {
        return gainProperty("mail.smtp.cc").getValue();
    }

    /**
     * 密送
     *
     * @return
     */
    public String gainMailSmtpBcc() {
        return gainProperty("mail.smtp.bcc").getValue();
    }

    /**
     * 主题
     *
     * @return
     */
    public String gainMailSmtpSubject() {
        return gainProperty("mail.smtp.subject").getValue();
    }

}
