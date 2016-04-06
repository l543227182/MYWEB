package cn.lc.domain;

import java.util.Date;

public class Notice {

	
	/*
	  
	  create table notice(
	    id varchar(40) not null primary key,
	    title varchar(40) not null,
	    username varchar(45) not null,
	    body Text,
	    time date,
	    path varchar(50),
	    constraint username_FK foreign key(username) references user(username) 
	  )ENGINE=InnoDB DEFAULT CHARSET=utf8;
	   
	 */
	private String id;
	private String title;
	private String username;
	private String body;
	private String path;
	private Date time;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}	
}
