package cn.lc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.Notice;
import cn.lc.domain.Page;
import cn.lc.domain.UPFile;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.WebResource;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	private BusinessServiceImpl bs=new BusinessServiceImpl();
	
    @Override
    public String execute() throws Exception {
    	// 文章爬取
    	//<h2 class="kb_entry">([\w\W]*?)</h2>([\w\W]*?)<div class="kb_summary">([\w\W]*?)</div>([\w\W]*?)<div class="kb_footer">([\w\W]*?)</div>
    	List<String> list=WebResource.geturlResouce("http://home.cnblogs.com/kb/","<h2 class=\"kb_entry\">(([\\w\\W]*?)</div>){2}");
    	//List<String> list=WebResource.geturlResouce("http://home.cnblogs.com/kb/","<h2 class=\"kb_entry\">([\\w\\W]*?)</h2>([\\w\\W]*?)<div class=\"kb_summary\">([\\w\\W]*?)</div>([\\w\\W]*?)<div class=\"kb_footer\">([\\w\\W]*?)</div>");
    	//System.out.println(list);
    	HttpServletRequest request=ServletActionContext.getRequest();
    	request.setAttribute("str", list);  
    	
    	List<UPFile> files=bs.findFilebystate(true);
    	//System.out.println(files);
    	//list.subList(1, list.size()>5?5:list.size())
    	request.setAttribute("files",files.subList(0, files.size()>5?5:files.size()));  	
    	/*
    	 notice 发布 
    	 */
    	Page p=bs.findNoticePageData(null);
    	List notices=p.getList();
    	request.setAttribute("notices", notices.subList(0, notices.size()>5?5:notices.size()));   	
    	return SUCCESS;
    }
    public String toNoticePage()
    {
    	  HttpServletRequest request=ServletActionContext.getRequest();
    	  String NoticeId=request.getParameter("noticeId");
    	  Notice notice=bs.findNoticeBYid(NoticeId);
    	  request.setAttribute("notice", notice);	
    	  return SUCCESS;
    }
}
