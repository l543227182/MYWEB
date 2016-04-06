package cn.lc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lc.dao.ArticleDao;
import cn.lc.domain.Article;
import cn.lc.utils.JdbcUtils;

public class ArticleDaoImpl implements ArticleDao {

	@Override
	public void addArticle(Article a) {
		// TODO Auto-generated method stub
      try {
		 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
		 String sql="insert into article(id,title,username,body,time,state) values(?,?,?,?,?,?)";
		 Object params[]={a.getId(),a.getTitle(),a.getUsername(),a.getBody(),a.getTime(),a.isState()};
	//	System.out.println(a.getBody());
		 runner.update(sql,params);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

	
	@Override
	public void updateArticle(Map<String, String> params) {
		
   try {
	 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
	 String sql="";
} catch (Exception e) {
	throw new RuntimeException(e);
}
	}

	@Override
	public Article findArticleByid(String id) {
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select *from article where id= ?";
			return (Article) runner.query(sql, id,new BeanHandler(Article.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Article> findArticleByUserName(String username) {
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select *from article where username= ?";
			return  (List<Article>) runner.query(sql, username,new BeanListHandler(Article.class));
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	}

	@Override
	public void deletArticlebyId(String id) {
		
		 try {
			 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			 String sql="delete from article where id=?";
			runner.update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletArticlebyUser(String username) {
	 try {
			 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			 String sql="delete from article where userId=?";
			runner.update(sql,username);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	}

}
