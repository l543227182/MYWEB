package cn.lc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lc.dao.NoticeDao;
import cn.lc.domain.Notice;
import cn.lc.utils.JdbcUtils;

public class NoticeDaoImpl implements NoticeDao {

	@Override
	public void addDao(Notice notice) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="insert into notice(id,title,username,body,time,path) values(?,?,?,?,?,?)";
		    Object[] params={notice.getId(),notice.getTitle(),notice.getUsername(),notice.getBody(),notice.getTime(),notice.getPath()};
		    runner.update(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="delete from notice where id = ?";
            runner.update(sql,id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public Notice findById(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select *from notice where id=? ";
          return (Notice) runner.query(sql,id,new BeanHandler(Notice.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Notice findByUsername(String username) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select *from notice where username=? ";
          return (Notice) runner.query(sql,username,new BeanHandler(Notice.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(String id, Map<String, String> map) {
		try {
			 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			 String sql="update notice set ";
			 for(Map.Entry<String, String> entry:map.entrySet()){
				 sql+=(entry.getKey()+" = '"+entry.getValue()+"',");	 
			 }
			 sql=sql.substring(0, sql.length()-1)+"where id= ?";
			 System.out.println(sql);
			 runner.update(sql,id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Notice> findByUsername(int Startindex, int pageSize,
			String username) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select *from notice where username = ? limit  ?,? ";
       //     System.out.println(sql);
            Object[] params={username,Startindex,pageSize};
        return (List<Notice>) runner.query(sql, params,new BeanListHandler(Notice.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public int GetCount() {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
             String sql="select count(*) from notice";
             long l=(Long) runner.query(sql, new ScalarHandler());
             return (int) l;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Notice> GetAll(int Startindex, int pageSize) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select *from notice limit  ?,? ";
            System.out.println(sql);
            Object[] params={Startindex,pageSize};
        return (List<Notice>) runner.query(sql, params,new BeanListHandler(Notice.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}

}
