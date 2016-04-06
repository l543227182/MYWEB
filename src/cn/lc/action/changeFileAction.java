package cn.lc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.Page;
import cn.lc.domain.UPFile;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class changeFileAction extends ActionSupport {
    private BusinessServiceImpl bs=new BusinessServiceImpl();
    private String pageNum;
    private String fileId;
    
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	@Override
	public String execute() throws Exception {
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if(u==null)
			return ERROR;
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

	public  String changeFile()	
	{
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u==null)
			return ERROR;
		UPFile file=bs.findFile_id(fileId);
		ServletActionContext.getRequest().setAttribute("file", file);
		return "changeFile";
	}
	private String fileName;
	private String state;
	private String ToUser;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getToUser() {
		return ToUser;
	}

	public void setToUser(String toUser) {
		ToUser = toUser;
	}

	public String updateFile()
	{
		//User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//if(u==null)
		//	return ERROR;
		Map<String, String > map=new HashMap<String, String>();
		if(ToUser==null&&state!=null&&fileName!=null)
			return "update";
		if(ToUser!=null&&ToUser.trim()!=""&&!ToUser.isEmpty())
		{
			if(bs.findFile_user(ToUser)==null){
				ServletActionContext.getRequest().setAttribute("message", "没有这样的用户注册，请确认后再填写");
				return "update";
				}
			map.put("upUser", ToUser);
		}
		 //System.out.println(state);
		if(state!=null)
		{			state=state.equals("1")?"1":"0";
		 // System.out.println(state);
			        map.put("state",state);
		}
		if(fileName!=null&&fileName.trim()!=""&&!fileName.isEmpty())
		{
			map.put("fileName", fileName);
		}
		System.out.println(fileId+"  "+map);
		bs.updateFile(fileId,map);
		HttpServletRequest  request= ServletActionContext.getRequest();
	   // System.out.println(ToUser+"  "+state+"     "+fileName+"  id="+fileId);
		request.setAttribute("message", "修改成功");
		return "update";
	}
}
