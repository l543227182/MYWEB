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
		session.setDebug(true);//���ú�Ὣ�ʼ���������Ľ������̴�ӡ����
		Message message=createMessage(session,email);
		
		Transport tp= session.getTransport();//�õ��ʼ����Ͷ���
		tp.connect("luocmail@sina.com","luochaolqfhv");
		tp.sendMessage(message, message.getAllRecipients());
	}

	private static Message createMessage(Session session,String email) throws Exception{
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress("luocmail@sina.com"));//������
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//���͸�˭
		message.setSubject("���,LC-Studio�����������֤��");
		
		MimeBodyPart text=new MimeBodyPart();//�õ�����������
		 validateCode=new Random().nextInt(999999);
		text.setContent("��֤��Ϊ:"+validateCode+"\n �뽫��֤������,���ע��","text/html;charset=UTF-8");//����
		
		//ͼƬ
	/*	
	    MimeBodyPart image=new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource("src/1.jpg")));
		image.setContentID("1.jpg");
	*/
		//����
		
		
		//������ϵ
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
		//message.writeTo(new FileOutputStream(""));//д������
		return message;
	}
}
