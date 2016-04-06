package cn.lc.service.imp;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.lc.dao.ArticleDao;
import cn.lc.dao.FileDao;
import cn.lc.dao.NoticeDao;
import cn.lc.dao.UserDao;
import cn.lc.dao.impl.UserDaoImpl;
import cn.lc.domain.Article;
import cn.lc.domain.Notice;
import cn.lc.domain.Page;
import cn.lc.domain.UPFile;
import cn.lc.domain.User;
import cn.lc.utils.DaoFactory;

public class BusinessServiceImpl {

	UserDao Udao=DaoFactory.getInstance().getDao("cn.lc.dao.impl.UserDaoImpl",UserDao.class);
	FileDao Fdao=DaoFactory.getInstance().getDao("cn.lc.dao.impl.FileDaoImpl", FileDao.class);	
	ArticleDao Adao=DaoFactory.getInstance().getDao("cn.lc.dao.impl.ArticleDaoImpl", ArticleDao.class);	
	public void addUser(User user)
	{
		Udao.add(user);
	}
	public User fingUserbyId(String id)
	{
		return Udao.find(id);
	}
	public User  findUser(String username)
	{
		return Udao.findUsername(username);
	}
	public User findUser(String username ,String password)
	{
		return Udao.find(username, password);
	}
	
	
	//对关于文件操作
	
	public void deleteFile(String id)
	{
		Fdao.delete(id);
	}
	public void addFile(UPFile file)
	{
		Fdao.add(file);
	}
	public UPFile findFile_id(String id)
	{
		return Fdao.findByid(id);
	}
	public List<UPFile> findFile_user(String username)
	{
		return Fdao.findByUser(username);
	}
	
	public List<UPFile> getAllfile(int startIndex,int pageSize)
	{
		return Fdao.getAllfile(startIndex, pageSize);
	}
	public List<UPFile> getAllfile(int startIndex,int pageSize ,String username)
	{
		
		return Fdao.getAllfile(startIndex, pageSize, username);
	}
	public InputStream getPhoto(String username)
	{
		return Udao.getPhoto(username);
	}
	public List<UPFile> findFilebystate(boolean state)
	{
		return Fdao.findFilebystate(state);
	}
	//修改文件的信息
	public void updateFile(String id,Map<String , String > map)
	{
		Fdao.updateFile(id, map);
	}
	public Page getAllfile(String pageNum)
	{
		int recordCount=Fdao.getRecord();
		Page p;
		if(pageNum==null)
		{
			p=new Page(1, recordCount);
		}
		else
		{
			p=new Page(Integer.valueOf(pageNum), recordCount);
		}
		p.setList(Fdao.getAllfile(p.getSqlIndexStart(), p.getPageSize()));
		return p;
	}
	//文件分页
	public Page getFilePageData(String pageNum,String username) {
		
	 int recordCount=Fdao.getRecord(username);
	 Page p;
	 if(pageNum==null)
	 {
		 p=new Page(1, recordCount);
	 }
	 else{
		 p=new Page(Integer.valueOf(pageNum), recordCount);
	 }
	 p.setList(Fdao.getAllfile(p.getSqlIndexStart(), p.getPageSize(), username));
	 return p;
	}
	
	
	
	/*
	 * 对文章的操作
	 * 
	 * */
	
	public void addArticle(Article a)
	{
		Adao.addArticle(a);
	}
	public void delArticlebyUser(String username)
	{
		Adao.deletArticlebyUser(username);
	}
	public void delArticlebyId(String id)
	{
		Adao.deletArticlebyId(id);
	}
	public Article findArticleByid(String id)
	{
		return Adao.findArticleByid(id);
	}
	public List<Article> findArticleByuser(String username)
	{
		return Adao.findArticleByUserName(username);
	}
	
	
	/* 对公告的操作*/
	NoticeDao Ndao=DaoFactory.getInstance().getDao("cn.lc.dao.impl.NoticeDaoImpl", NoticeDao.class);
	public void addNotice(Notice notice)
	{
		Ndao.addDao(notice);
	}
	public void deleteNotice(String id)
	{
		Ndao.delete(id);
	}
	public Notice findNoticeBYid(String 	id)
	{
	   return Ndao.findById(id);
	}
	public Notice findNoticleBYuse(String username)
	{
		return Ndao.findByUsername(username);
	}
	public Page findNoticePageData(String pageNum)
	{
		Page p;
	   int recordCount=Ndao.GetCount();
		if(pageNum==null)
			p=new Page(1, recordCount);
		else
			p=new Page(Integer.valueOf(pageNum), recordCount);
		List<Notice> list=Ndao.GetAll(p.getSqlIndexStart(), p.getPageSize());
		p.setList(list);
		return p;
	}
	public Page findNoticePageData(String pageNum,String username)
	{
		   Page p;
		   int recordCount=Ndao.GetCount();
			if(pageNum==null)
				p=new Page(1, recordCount);
			else
				p=new Page(Integer.valueOf(pageNum), recordCount);
			List<Notice> list=Ndao.findByUsername(p.getSqlIndexStart(), p.getPageSize(),username);
			if(list.size()>0?true:false)
			 p.setList(list);
			//System.out.println(p.getList());
			return p;
	}
}

