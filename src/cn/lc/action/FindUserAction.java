package cn.lc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FindUserAction extends ActionSupport {

	private String username;
	private BusinessServiceImpl bs = new BusinessServiceImpl();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() throws Exception {

	 HttpServletResponse response=ServletActionContext.getResponse();
	 response.setContentType("text/html;charset=utf-8");
	 PrintWriter pw=response.getWriter();
	    User user=bs.findUser(username);
		if (user== null) {
			pw.print("0");
			//System.out.println("00000");
		}
		else {
			pw.print("1");
			//System.out.println("11111");
		}
       ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
		//System.out.println(username+"   "+user);
		return SUCCESS;
	}
}
