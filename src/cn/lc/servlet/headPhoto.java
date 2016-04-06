package cn.lc.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lc.domain.User;

public class headPhoto extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            User u=(User) request.getSession().getAttribute("user");
            if(u==null)
            	return ;
            response.setHeader("content-tyep", "image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
           // System.out.println(u.getImage());
            BufferedImage image=ImageIO.read(new ByteArrayInputStream(u.getImage()));
            ImageIO.write(image, "jpg", response.getOutputStream());
            response.getOutputStream().flush();
            //System.out.println("Œ“¿¥¡À");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
