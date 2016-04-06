package cn.lc.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.Notice;
import cn.lc.domain.Page;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.Webutils;

import com.opensymphony.xwork2.ActionSupport;

public class publicNotice extends ActionSupport {
 
  private BusinessServiceImpl bs=new BusinessServiceImpl();
  @Override
  public String execute() throws Exception {
	  HttpServletRequest request=ServletActionContext.getRequest();
	  User u=(User) request.getSession().getAttribute("user");
	  if(u==null)
	  return ERROR;
	  Page p= bs.findNoticePageData(null, u.getUsername());	 
	  request.setAttribute("page",p);		  
	  return SUCCESS;
  }
  public boolean isLogin( HttpServletRequest request)
  {
	  User u=(User) request.getSession().getAttribute("user");
	  if(u==null)
	  return false;
	  return true;
  }
  public String toPage()
  {
	  HttpServletRequest request=ServletActionContext.getRequest();
	  if(!isLogin(request))
		  return ERROR;
	  return SUCCESS;
  }
  private Notice notice=new Notice();
  
  public Notice getNotice() {
	return notice;
  }
  public void setNotice(Notice notice) {
	this.notice = notice;
  }
  public String addNotice()
  {
	  HttpServletRequest request=ServletActionContext.getRequest();
	  
	  if(!isLogin(request))
		  return ERROR;

	  User u=(User) request.getSession().getAttribute("user");
	  notice.setId(Webutils.MakeId());
	  notice.setPath("");
	  notice.setTime(new Date());
	  notice.setUsername(u.getUsername());
	  bs.addNotice(notice);
	  System.out.println(notice);
	  String m = "<meta http-equiv='refresh' content='3;url=${pageContext.request.contextPath}/publicNotice.do'>保存成功，<span id='second'></span>秒后自动跳转，如果失败请点击<a href='${pageContext.request.contentPath}/publicNotice.do'>这里</a>";
      request.setAttribute("message", m);
	  return SUCCESS;
  }
  
  public String deleteNotice()
  {
	  HttpServletRequest request=ServletActionContext.getRequest(); 
	  if(!isLogin(request))
		  return ERROR;
	  String NoticeId=request.getParameter("noticeId");
	  bs.deleteNotice(NoticeId);
	  String m = "<meta http-equiv='refresh' content='3;url=${pageContext.request.contextPath}/publicNotice.do'>删除成功，<span id='second'></span>秒后自动跳转，如果失败请点击<a href='${pageContext.request.contentPath}/publicNotice.do'>这里</a>";
      request.setAttribute("message", m);
	  return SUCCESS;
  }
  
  public String LookUpNotice()
  {
	  HttpServletRequest request=ServletActionContext.getRequest(); 
	  if(!isLogin(request))
		  return ERROR;
	  String NoticeId=request.getParameter("noticeId");
	  Notice notice=bs.findNoticeBYid(NoticeId);
	  request.setAttribute("notice", notice);	
	  return SUCCESS;
  }
  
}
