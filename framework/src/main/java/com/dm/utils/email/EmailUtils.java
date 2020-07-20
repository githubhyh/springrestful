package com.dm.utils.email;

import com.alibaba.fastjson.JSONObject;
import com.dm.config.properties.JavaMailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    private static JavaMailProperties mailProperties;

    @Autowired
    public void setMailProperties(JavaMailProperties javaMailProperties) {
        logger.info("init mail config " + JSONObject.toJSONString(javaMailProperties));
        EmailUtils.mailProperties = javaMailProperties;
    }

    /**
     * 发送一封简单邮件
     * */
    public static boolean sendEmail(String name, String to, String copyTo, String subject, String content) {
        boolean result = false;
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        properties.setProperty("mail.host", mailProperties.getHostname());
        properties.setProperty("mail.smtp.auth", mailProperties.getSmtpAuth());
        EmailAuthentication authentication = new EmailAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
        Session session = Session.getInstance(properties, authentication);
        session.setDebug(true);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getFrom(), name, "utf-8"));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyTo));
            message.setSentDate(new Date());
            message.setSubject(subject);

            //邮件正文
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);
            message.saveChanges();

            //发送邮件
            Transport.send(message);
            result = true;
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {

        }
        return result;
    }
}
