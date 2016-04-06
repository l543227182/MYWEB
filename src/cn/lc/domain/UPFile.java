package cn.lc.domain;

import java.util.Date;

public class UPFile {

	/*
	  create table file(
	     id varchar(100) not null primary key ,
	     fileName varchar(20),
	     state boolean,
	     path varchar(100) not null,
	     upTime date,
	     upUser varchar(50) not null,
	     fileSize long ,
	     constraint upUser_FK foreign key (upUser) references user(id)
	  )character set utf8 collate utf8_general_ci;
	 */
	private String id;
	private String fileName;
	private boolean state;
	private String path;
	private Date upTime;
	private String upUser;
	private long fileSize;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	public String getUpUser() {
		return upUser;
	}
	public void setUpUser(String upUser) {
		this.upUser = upUser;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
}
