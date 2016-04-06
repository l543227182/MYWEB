package cn.lc.domain;

import java.awt.Image;
import java.util.Date;

public class User {
  
	/*
	
  create table User( 
   id varchar(40) primary key,
   name varchar(10) not null,
   gender varchar(10) not null,
   age int,
   username varchar(40) not null,
   password varchar(80) not null,
   email varchar(40),
   birthday date,
   cellphone varchar(20),
   registerTime date
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/
	private String id;
	private String name;
	private String gender;
	private Integer age;
	private String username;
	private String password;
	private String email;
	private Date birthday;
	private String cellphone;
	private Date registerTime;
	private byte[] image;
	private boolean isManager;
	
	
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
