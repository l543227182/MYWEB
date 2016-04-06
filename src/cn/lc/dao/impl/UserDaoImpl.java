package cn.lc.dao.impl;

import java.awt.Image;
import java.io.InputStream;
import java.sql.*;

import javax.imageio.ImageIO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.lc.dao.UserDao;
import cn.lc.domain.User;
import cn.lc.utils.JdbcUtils;


public class UserDaoImpl implements UserDao {

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,name,gender,age,username,password,email,birthday,cellphone,registerTime) values(?,?,?,?,?,?,?,?,?,?)";
			Object param[]={user.getId(),user.getName(),user.getGender(),user.getAge(),user.getUsername(),user.getPassword(),user.getEmail(),user.getBirthday(),user.getCellphone(),user.getRegisterTime()};
			runner.update(sql,param);			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User find(String id) {
	
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select *from user where id=?";
			return	(User) runner.query(sql,id,new BeanHandler(User.class));			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User find(String username, String password) {

		try{
			//select *from user where password='a',username='b';
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select *from user where password=? and username=?";
			Object params[]={password,username};
			return (User) runner.query(sql,params,new BeanHandler(User.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public User findUsername(String username) {

		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select *from user where username=?";
			return	(User) runner.query(sql,username,new BeanHandler(User.class));			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String id =user.getId();
		try{
			String id1=user.getId();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from user where id = ?";
			runner.update(sql,id1);
			add(user);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public InputStream getPhoto(String username) {
		try{		
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select image from user where username=?";
			Connection conn=JdbcUtils.getConnection();
			PreparedStatement pstt=conn.prepareStatement(sql);
			pstt.setString(1, "lc");
	        ResultSet rs=pstt.executeQuery();
	        rs.next();
	        InputStream is=rs.getBinaryStream("image");	       
	        return is;	 
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
