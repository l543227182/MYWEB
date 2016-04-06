package cn.lc.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.lc.dao.FileDao;
import cn.lc.domain.UPFile;
import cn.lc.utils.JdbcUtils;

public class FileDaoImpl implements FileDao {

	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
       try{
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
		String sql="delete from file where id = ?";
		runner.update(sql, id);
       }catch(Exception e)
       {
    	   throw new RuntimeException(e);
       }
	}

	@Override
	public UPFile findByid(String id) {
		 try{
				QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
				String sql="select *from file where id=?";		
				return (UPFile) runner.query(sql, id,new BeanHandler(UPFile.class));
		       }
		       catch(Exception e)
		       {
		    	   throw new RuntimeException(e);
		       }
	}

	@Override
	public List<UPFile> findByUser(String username) {
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select *from file where upUser=?";		
			return (List<UPFile>) runner.query(sql, username,new BeanListHandler(UPFile.class));
	       }
		   catch(Exception e)
	       {
	    	   throw new RuntimeException(e);
	       }
	}

	@Override
	public void add(UPFile file) {
		   try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into file(id,fileName,state,path,upTime,upUser,fileSize) values(?,?,?,?,?,?,?)";	
		    Object[] params={file.getId(),file.getFileName(),file.isState(),file.getPath(),file.getUpTime(),file.getUpUser(),file.getFileSize()};
			runner.update(sql,params);
	       }
		   catch(Exception e)
	       {
	    	   throw new RuntimeException(e);
	       }
	}



	@Override
	public int getRecord() {
          try {
			  QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			  String sql="select count(*) from file";
			  long l=(Long) runner.query(sql,new ScalarHandler());
			  return (int) l;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getRecord(String username) {
		   try {
				 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
				  String sql="select count(*) from file where upUser = ? ";
				  //long l=runner.update(sql,username,new ScalarHandler());
				  long l=(Long) runner.query(sql,username,new ScalarHandler());
				  return (int) l;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public List<UPFile> getAllfile(int startIndex, int pageSize, String username) {
		
	   try {
		   //在用户名为1 下从第二条开始取三条数据 
		   //select *from file  where upUser =1 limit 2,3;
		 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
		String sql="select *from file  where upUser =? limit ?,?";
		Object[] params={username,startIndex,pageSize};
		return  (List<UPFile>) runner.query(sql,params,new BeanListHandler(UPFile.class));
	} catch (Exception e) {
		// TODO: handle exception
		throw new RuntimeException(e);
	}
	}

	@Override
	public List<UPFile> getAllfile(int startIndex, int pageSize) {
		   try {
			   //在用户名为1 下从第二条开始取三条数据 
			   //select *from file  where upUser =1 limit 2,3;
			 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			 String sql="select *from file limit ?,?";
			 Object[] params={startIndex,pageSize};
			return  (List<UPFile>) runner.query(sql,params,new BeanListHandler(UPFile.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public void changeState(String id) {
		// TODO Auto-generated method stub
		
	}
   public void updateFile(String id,Map<String, String> map)
   {
	   try {
		 QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
		 String sql="update file set ";
		 for(Map.Entry<String, String> entry:map.entrySet()){
			 sql+=(entry.getKey()+" = '"+entry.getValue()+"',");	 
		 }
		 sql=sql.substring(0, sql.length()-1)+"where id= ?";
		 System.out.println(sql);
		 runner.update(sql,id);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
   }
   
	@Override
	public List<UPFile> findFilebystate(boolean state) {
       try {			
    	    int param=state==true?1:0;
    	    QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
    	    String sql="select *from file where state = ?";
    	    return  (List<UPFile>) runner.query(sql, param, new BeanListHandler(UPFile.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UPFile> getAllfile() {
		  try {			
	    	 
	    	    QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
	    	    String sql="select *from file";
	    	    return  (List<UPFile>) runner.query(sql, new BeanListHandler(UPFile.class));
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
	}

}
