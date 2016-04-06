package cn.lc.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.IIOByteBuffer;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.ImageOutputStreamImpl;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.Article;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.Webutils;

import com.opensymphony.xwork2.ActionSupport;

public class personalSpace extends ActionSupport {
	private BusinessServiceImpl bs = new BusinessServiceImpl();
	private Article a;

	public Article getA() {
		return a;
	}

	public void setA(Article a) {
		this.a = a;
	}

	@Override
	public String execute() throws Exception {

		BusinessServiceImpl bs = new BusinessServiceImpl();
		User u = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (u == null)
			return "loginTospace";
		else {
			String username = u.getUsername();
			List<Article> list = bs.findArticleByuser(username);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("articles", list.size() > 0 ? list : null);
			return SUCCESS;
		}
	}

	// 格式保存
	public String dealArticleBody(String text) {
		char[] content = new char[text.length()];
		text.getChars(0, text.length(), content, 0);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			switch (content[i]) {
			case ' ':
				result.append("&nbsp;");
				break;
			case '\n':
				result.append("<br>");
				break;

			default:
				result.append(content[i]);
				break;
			}
		}
		return result.toString();
	}

	public String deleteArticle() {
		HttpServletRequest request = ServletActionContext.getRequest();

		User u = (User) request.getSession().getAttribute("user");
		if (u == null) {

			return ERROR;
		}
		String articleId = request.getParameter("articleId");
		bs.delArticlebyId(articleId);
		String m = "<meta http-equiv='refresh' content='3;url=${pageContext.request.contentPath}/personalSpace.do'>删除成功，<span id='second'></span>秒后自动跳转，如果失败请点击<a href='${pageContext.request.contentPath}/personalSpace.do'>这里</a>";
		request.setAttribute("message", m);
		return SUCCESS;
	}

	// 保存文章
	public String addArticle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User u = (User) request.getSession().getAttribute("user");
		if (u == null) {
			return ERROR;
		}
		a.setBody(dealArticleBody(a.getBody()));
		// System.out.println(a.getBody());
		a.setId(Webutils.MakeId());
		a.setState(false);
		a.setTime(new Date());
		a.setUsername(u.getUsername());
		bs.addArticle(a);
		String m = "<meta http-equiv='refresh' content='3;url=${pageContext.request.contentPath}/personalSpace.do'>"
				+ "保存成功，<span id='second'></span>秒后自动跳转，如果失败请点击<a href='${pageContext.request.contentPath}/personalSpace.do'>这里</a>";
		request.setAttribute("message", m);
		return SUCCESS;
	}

	// 写文章页面 不能用servlet跳转 路径会变..//提交表单的时候 路径会变成 WEB-INF/personal/下面的路径

	public String toWritePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User u = (User) request.getSession().getAttribute("user");
		if (u == null) {
			return ERROR;
		}
		return "toWritePage";
	}

	// 显示文章页面
	public String toArticlePage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String articleId = request.getParameter("articleId");
		// System.out.println(articleId);
		Article a = bs.findArticleByid(articleId);
		request.setAttribute("article", a);
		return "toArticlePage";
	}
}
