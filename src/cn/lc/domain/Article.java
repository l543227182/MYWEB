package cn.lc.domain;

import java.util.Date;

/*
 create table article(
  id varchar(40) primary key,
  title varchar(40) not null,
  userId varchar(40) not null,
  time date,
  body  Text
 )ENGINE = InnoDB;  
*/
public class Article {
private String id;
private String title;
private String username;
private Date time;
private String body;
private boolean state;
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
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public boolean isState() {
	return state;
}
public void setState(boolean state) {
	this.state = state;
}

}
