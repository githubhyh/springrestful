package com.dm.config.properties;

import com.dm.common.pojo.Weight;
import com.dm.validate.MailValidate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author hu.yuhao
 * @date 2020-7-2
 * 宽松绑定策略
 * 私有属性必须要有公有的setter方法
 *
 * 自定义参数绑定验证器
 * 自定义属性转换器
 * */
//@Component
@ConfigurationProperties(prefix = "java.mail")
//@MailValidate
//@Validated
public class JavaMailProperties {
    //邮件协议
    private String protocol;

    //邮件服务器地址
    private String hostname;

    //是否开启验证，默认开启
    private String smtpAuth = "true";

    //发件人
    private String from;

    //用户名
    private String username;

    //密码
    private String password;

    private Weight weight;

    public String getProtocol() {
        return protocol;
    }

    //@MailValidate
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    /*public class Weight {
        private double mass;

        private MassUnit massUnit;

        public double getMass() {
            return mass;
        }

        public void setMass(double mass) {
            this.mass = mass;
        }

        public MassUnit getMassUnit() {
            return massUnit;
        }

        public void setMassUnit(MassUnit massUnit) {
            this.massUnit = massUnit;
        }
    }*/
}
