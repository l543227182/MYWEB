package cn.lc.utils;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class SendMail {

	/*	
    @Test
    public void  test() throws Exception
	{
		SendMail.SendEmail("543227182@qq.com");
		System.out.println("over");
	}
	*/
	public static int  validateCode;
	public static void SendEmail(String email) throws Exception {
		Properties p=new Properties();
		p.setProperty("mail.smtp.host", "smtp.sina.com");
		p.setProperty("mail.transport.protocol", "smtp");
		p.setProperty("mail.smtp.auth", "true");
		
		javax.mail.Session session=javax.mail.Session.getInstance(p);
		session.setDebug(true);//设置后会将邮件与服务器的交互过程打印出来
		Message message=createMessage(session,email);
		
		Transport tp= session.getTransport();//得到邮件发送对象
		tp.connect("luocmail@sina.com","luochaolqfhv");
		tp.sendMessage(message, message.getAllRecipients());
	}

	private static Message createMessage(Session session,String email) throws Exception{
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress("luocmail@sina.com"));//发送人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//发送给谁
		message.setSubject("你好,LC-Studio发送你给的验证码");
		
		MimeBodyPart text=new MimeBodyPart();//得到正文体容器
		 validateCode=new Random().nextInt(999999);
		text.setContent("验证码为:"+validateCode+"\n 请将验证码输入,完成注册","text/html;charset=UTF-8");//正文
		
		//图片
	/*	
	    MimeBodyPart image=new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource("src/1.jpg")));
		image.setContentID("1.jpg");
	*/
		//附件
		
		
		//描述关系
		MimeMultipart part=new MimeMultipart();
		part.addBodyPart(text);
		part.setSubType("related");
		
	    /*	
	    MimeMultipart part2=new MimeMultipart();
		MimeBodyPart body=new MimeBodyPart();
		body.setContent(part);
		part2.addBodyPart(body);
		part2.setSubType("mixed");
		*/
		message.setContent(part);
		message.saveChanges();
		//message.writeTo(new FileOutputStream(""));//写入流中
		return message;
	}
}
