package cn.lc.action;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.User;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null)
			return ERROR;
		   
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return SUCCESS;
	}

}
