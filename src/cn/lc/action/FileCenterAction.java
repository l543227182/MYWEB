package cn.lc.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.Page;
import cn.lc.domain.UPFile;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class FileCenterAction extends ActionSupport {
   private  BusinessServiceImpl bs=new BusinessServiceImpl();
     private String pageNum;
     
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u==null)
			return "userNull";
		String username=u.getUsername();
		List list=bs.findFile_user(username);
		//判断用户是否上传有上传文件
		if(list.isEmpty())
		  return SUCCESS;
		Page p=bs.getFilePageData(pageNum, username);
		//System.out.println(p);
		ServletActionContext.getRequest().setAttribute("page",p);
		  return SUCCESS;
	}
	public String upFile()
	{
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u==null)
			return "userNull";
		return SUCCESS;
	}
	public String toFileRecord()
	{
		String pageNum=ServletActionContext.getRequest().getParameter("pageNum");
		Page page=bs.getAllfile(pageNum);
		ServletActionContext.getRequest().setAttribute("page", page);
		return SUCCESS;
	}
}
