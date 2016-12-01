package mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by noodles on 16/4/22.
 */
public class SendEmail
{
    public static void main(String [] args) throws MessagingException, GeneralSecurityException, UnsupportedEncodingException {

        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);


        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("报表");
        msg.setText("RT");
        msg.setFrom(new InternetAddress("542223490@qq.com"));


        File attachment = new File("/Users/noodles/ThirdssidPb.proto");

        // 添加附件的内容
        Multipart multipart = null;
        if (attachment != null) {

            multipart = new MimeMultipart();

            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
        msg.setContent(multipart);

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "542223490@qq.com", "vzolsnrfbpjdbdbb");

        transport.sendMessage(msg, new Address[]{new InternetAddress("hlyhexiansheng@163.com"), new InternetAddress("noodles.he@uxin.com")});
        transport.close();

    }
}