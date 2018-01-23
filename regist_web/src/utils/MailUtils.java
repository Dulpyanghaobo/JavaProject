package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.search.RecipientStringTerm;

public class MailUtils {
	/*
	 * 发送邮件的方法
	 * @param to:给谁发邮件
	 * @param code:邮件的激活码
	 * */
public static void sendMail(String to,String code) throws AddressException, MessagingException {
	//1.创建连接对象，链接到邮箱服务器
	Properties props = new Properties();
	Session session = Session.getInstance(props, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("service@dulp.com", "123");
		}
	
	
	});
	//2.创建一个邮件对象
	Message mess = new MimeMessage(session);
	//2.1设置发件人:
	mess.setFrom(new InternetAddress("service@dulp.com"));
	//2.2设置收件人:
	mess.setRecipient(RecipientType.TO,new InternetAddress(to));
	//2.3设置邮件的主题
	mess.setSubject("激活邮件欢迎来到Dulp world");
	//2.4.发送一封激活邮件
	mess.setContent("<h1>来自Dulp网站的激活邮件,请点击以下链接:</h1><h3><a href='http://localhost:8080/regist_web/ActiveServlet?code="+code+"'>http://localhost:8080/regist_web/ActiveServlet?code="+code+"</a></h3>", "text/html;charset=utf-8");
	//3.发送一封激活邮件
	Transport.send(mess);
}
}
