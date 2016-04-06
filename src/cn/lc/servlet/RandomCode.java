package cn.lc.servlet;

import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RandomCode extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   response.setHeader("content-tyep", "image/jpeg");
           BufferedImage buffer=new BufferedImage(82, 30, BufferedImage.TYPE_INT_RGB);
           Graphics g=buffer.getGraphics();         
           g.setFont(new Font(null,Font.BOLD,30));
           setBackGround(g);
           setBorder(g);
           drawRandomLine(g);
           g.setColor(Color.gray);
           makenum((Graphics2D) g);
           response.setDateHeader("expries", -1);
           response.setHeader("Cache-Control", "no-cache");
           response.setHeader("Pragma", "no-cache");
           ImageIO.write(buffer, "jpg", response.getOutputStream());
	}

public void  makenum(Graphics2D g)
{     int num=new Random().nextInt(999999);
      String n=num+"";
      StringBuilder sb=new StringBuilder();
      for(int i=0;i<6-n.length();i++)
      {
    	   sb.append(new Random().nextInt(9)+"");
      }
      String str=sb.toString()+n;
      int y=0;
      for(int i=0;i<str.length();i++)
      {
    	  int degree=new Random().nextInt(45);
    	  char x=str.charAt(i);
    	  g.rotate(degree*3.14/182, y, 22);
    	  g.drawString(String.valueOf(x), y,22);
    	  g.rotate(-degree*3.14/182, y, 22);
    	  y+=13;
      }
}
public void setBackGround(Graphics g)
{
	 g.setColor(Color.white);
	 g.fillRect(0, 0, 82, 30);
}
 public void setBorder(Graphics g)
 {
	 g.setColor(Color.blue);
	 g.drawRect(1, 1, 82-2, 30-2);
 }
 
 public void drawRandomLine(Graphics g)
 {
	 g.setColor(Color.gray);
	 for(int i=0;i<15;i++)
	 {
		 int x1=new Random().nextInt(82);
		 int y1=new Random().nextInt(30);
		 
		 int x2=new Random().nextInt(82);
		 int y2=new Random().nextInt(30);
		 
		 g.drawLine(x1, y1, x2, y2);
	 }
 }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doGet(request, response);
	}
}