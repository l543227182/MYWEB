package cn.lc.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.Webutils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class RegisterAction extends ActionSupport {
	private User user;
	private BusinessServiceImpl bs = new BusinessServiceImpl();
    private String ValidationCode;
    
	public String getValidationCode() {
		return ValidationCode;
	}

	public void setValidationCode(String validationCode) {
		ValidationCode = validationCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			user.setId(Webutils.MakeId());
			user.setRegisterTime(new Date());		
			user.setGender(user.getGender().equals("1")?"男":"女");
			user.setManager(false);
			System.out.println(user.getEmail());
			ValueStack vs=ActionContext.getContext().getValueStack();
			String ValidateCode=Webutils.MakeId();
			vs.set("ValidateCode", ValidateCode);
			ServletActionContext.getServletContext().setAttribute(ValidateCode, user);
			return SUCCESS;
		} catch (Exception e) {
			addActionError("注册失败,请稍后再注册!!");
            return INPUT;
		}
	}
	
	public String Isvalidation()
	{
		String flag=(String) ServletActionContext.getRequest().getParameter("validataCode");
		List<Object> list=(List<Object>) ServletActionContext.getServletContext().getAttribute(flag);
		//System.out.println("提交时的:"+list);
		User u=(User) list.get(0);
		String ServiceCode=String.valueOf(list.get(1));
		if(ServiceCode.trim().equals(ValidationCode))
		{
			bs.addUser(u);
			ServletActionContext.getRequest().getSession().setAttribute("user",u);
			ServletActionContext.getServletContext().removeAttribute(flag);
			return SUCCESS;
		}
		else
		{
			ServletActionContext.getRequest().setAttribute("ValidateCode", flag);
		    ServletActionContext.getServletContext().setAttribute(flag,list);
			addActionError("验证码错误!!!请确认后再输入");
			return ERROR;
		}
	}
}
