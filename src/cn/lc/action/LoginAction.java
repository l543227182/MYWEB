package cn.lc.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.FieldError;
import org.apache.struts2.views.velocity.components.FieldErrorDirective;

import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
  private String password;
  private String username;
  private BusinessServiceImpl bs=new BusinessServiceImpl();
  public void setPassword(String password) {
	this.password = password;
}

public void setUsername(String username) {
	this.username = username;
}

   public String login()
  {
	   if(password.trim().equals("")||username.trim().equals(""))
	   {
		   addActionError("用户名或密码不能为空");
		   return INPUT;
	   }
	   User user=bs.findUser(username, password);
	   if(user==null)
	   {
		 addActionError("用户名或密码错误");
	     return INPUT;
	   }
	   ServletActionContext.getRequest().getSession().setAttribute("user", user);
	   return "redirect";
  }
   
}
