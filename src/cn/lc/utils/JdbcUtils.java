package cn.lc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static DataSource ds;
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	static {
		ds = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() throws Exception {
		try {
			Connection conn = t.get();
			if (conn == null) {
				conn = ds.getConnection();
				t.set(conn);
			}
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public  static void startTransaction()
	{
		try {
			Connection conn=getConnection();
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public static void commit()
	{
		try{
			Connection conn=t.get();
			if(conn!=null)
				conn.commit();
		}catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void rollback()
	{
		try
		{
				Connection conn=t.get();
				if(conn!=null)
					conn.rollback();
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	public static void close()
	{
		try
		{
				Connection conn=t.get();
				if(conn!=null){
					conn.close();
					t.remove();
					}
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
