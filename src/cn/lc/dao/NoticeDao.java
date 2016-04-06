package cn.lc.dao;

import java.util.List;
import java.util.Map;

import cn.lc.domain.Notice;

public interface NoticeDao {
  public void addDao(Notice notice);
  public void delete(String id);
  public Notice findById(String id);
  public Notice findByUsername(String username);
  public void update(String id,Map<String, String> map);
  public List<Notice> findByUsername(int Startindex,int pageSize,String username);
  public List<Notice> GetAll(int Startindex,int pageSize);
  public int GetCount();
}
