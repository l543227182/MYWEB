package cn.lc.dao;

import java.util.List;
import java.util.Map;

import cn.lc.domain.Article;

public interface ArticleDao {
  public void addArticle(Article a);
  public void deletArticlebyId(String id);
  public void deletArticlebyUser(String username);
  public void updateArticle(Map<String , String > params);
  public Article findArticleByid(String id);
  public List<Article> findArticleByUserName(String username);
}
