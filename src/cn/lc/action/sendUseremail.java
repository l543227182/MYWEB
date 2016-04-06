package cn.lc.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.User;
import cn.lc.utils.SendMail;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class sendUseremail extends ActionSupport {

	private String UserFlag;
	
	public String getUserFlag() {
		return UserFlag;
	}

	public void setUserFlag(String userFlag) {
		UserFlag = userFlag;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		User user=(User) ServletActionContext.getServletContext().getAttribute(UserFlag);
	   // System.out.println(user.getGender());
		SendMail.SendEmail(user.getEmail());
		int validataCode=SendMail.validateCode;
		ArrayList<Object> list=new ArrayList<Object>();
		list.add(user);
		list.add(validataCode);
		ServletActionContext.getRequest().setAttribute("ValidateCode", UserFlag);
		ServletActionContext.getServletContext().setAttribute(UserFlag,list);
		return SUCCESS;
	}
}
