package mail;

import lombok.Data;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by noodles on 16/5/21.
 */
public class ApacheEmail {

    public static boolean send(Mail mail) {
        // 发送email
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName(mail.getHost());
            // 字符编码集的设置
            email.setCharset(Mail.ENCODEING);
            // 收件人的邮箱
            email.addTo(mail.getReceiver());
            // 发送人的邮箱
            email.setFrom(mail.getSender(), mail.getName());
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(mail.getUsername(), mail.getPassword());
            // 要发送的邮件主题
            email.setSubject(mail.getSubject());
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(mail.getMessage());

//            EmailAttachment attachment =new EmailAttachment();
//            attachment.setPath("/tmp/a.txt");// 本地文件
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("a.txt");
//            attachment.setName("a.txt");
//
//            email.attach(attachment);
            // 发送
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args){
        Mail mail = new Mail();
//        mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
//        mail.setSender("hlyhexiansheng@163.com");
//        mail.setReceiver("542223490@qq.com"); // 接收人
//        mail.setUsername("hlyhexiansheng@163.com"); // 登录账号,一般都是和邮箱名一样吧
//        mail.setPassword("!mykeysqq!"); // 发件人邮箱的登录密码
//        mail.setSubject("cccc");
//        mail.setMessage("中文为");

        mail.setHost("mail.uxin.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
        mail.setSender("noodles.he@uxin.com");
        mail.setReceiver("hlyhexiansheng@163.com"); // 接收人
        mail.setUsername("noodles.he@uxin.com"); // 登录账号,一般都是和邮箱名一样吧
        mail.setPassword("123my.com"); // 发件人邮箱的登录密码
        mail.setSubject("laiziyouxin");
        mail.setMessage("<b>中文为</b><a href=\"http://www.yuantongxun.com\">地址</a><img  src=\"//www.baidu.com/img/bd_logo1.png\" width=\"270\" height=\"129\">");

        send(mail);
    }
}
@Data
class Mail{
    public static final String ENCODEING = "UTF-8";

    private String host; // 服务器地址

    private String sender; // 发件人的邮箱

    private String receiver; // 收件人的邮箱

    private String name; // 发件人昵称

    private String username; // 账号

    private String password; // 密码

    private String subject; // 主题

    private String message; // 信息(支持HTML)
}
