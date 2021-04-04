package com.mmtax.common.utils;

import cn.hutool.core.date.DateUtil;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/22 13:52
 */
public class MailUtil {

    public static void sendEmail(List<String> list, BigDecimal amount) throws Exception {
        Properties properties = new Properties();
        // 连接协议
        properties.put("mail.transport.protocol", "smtp");
        // 主机名
        properties.put("mail.smtp.host", "smtp.exmail.qq.com");
        // 端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.smtp.ssl.enable", "true");
        // 设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.debug", "true");
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("yc@meimiaohealth.com"));
        InternetAddress[] addresses = new InternetAddress[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.isNotEmpty(list.get(i))) {
                InternetAddress address = new InternetAddress(list.get(i));
                addresses[i] = address;
            }
        }
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO, addresses);
        // 设置邮件标题
        message.setSubject("【安薪宝】");
        // 设置邮件内容
        message.setText("【余额提醒】尊敬的客户：您好！截至" + DateUtil.now() + "，您的余额已少于预设值" + amount +
                "元。请及时注意余额，以免影响正常出款流程。若拒收此邮件，请在安薪宝后台关闭邮件预警值提醒。（此类邮件请勿回复）");
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        transport.connect("yc@meimiaohealth.com", "Meimiao123");
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
