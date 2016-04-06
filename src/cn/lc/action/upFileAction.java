package cn.lc.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.lc.domain.UPFile;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.Webutils;

import com.opensymphony.xwork2.ActionSupport;

public class upFileAction extends ActionSupport {
    private BusinessServiceImpl bs=new BusinessServiceImpl();
	private String filename;
	private File photo;
	private String  photoFileName;
	private String  photoContentType;   
	private String beizhu;
	private int state;
	

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	
	public void addActionError(String anErrorMessage){
		System.out.println(anErrorMessage);
		if (anErrorMessage.startsWith("the request was rejected because its size")) {
		                     
			      ServletActionContext.getRequest().setAttribute("message", "对不起，你上传的文件太大，服务器拒绝接受，请上传50M以下的文件！");
		            super.addActionError(anErrorMessage);
		        } else {
		            super.addActionError(anErrorMessage);
		        }
		}
	
	public String UpFile() throws IOException
	{
		//String path=ServletActionContext.getServletContext().getRealPath("");
		UPFile up=new UPFile();
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(photo==null)
		{
			ServletActionContext.getRequest().setAttribute("message", "呵呵，加个文件吧");
			return ERROR;
		}
		if(u==null){
			ServletActionContext.getRequest().setAttribute("message", "登录或注册后再来咯！！！");
			return ERROR;
		}
		if(photo.length()>51699500)
		{
			ServletActionContext.getRequest().setAttribute("message", "文件太大了，只能在50M以下！！");
			return ERROR;
		}
		if(!filename.isEmpty()){
			photoFileName=filename+"."+photoFileName.substring(photoFileName.lastIndexOf(".")+1);
		    System.out.println("不是空的");	
		    up.setFileName(filename);
		}else{
			up.setFileName(photoFileName);
		}
		System.out.println(photoFileName+"     "+photoContentType);
		String path="C:\\Users\\Administrator\\Desktop\\文件\\JavaupFile";		
		photoFileName=MakeFilename(photoFileName);
		path=makePath(photoFileName, path);
		System.out.println("保存路径："+path+"\n名字为:"+photoFileName);
		File file2=new File(path,photoFileName);
		
		
		up.setFileSize(photo.length()/1024);
		up.setId(Webutils.MakeId());
		up.setPath(photoFileName);
		up.setState(state==1?true:false);
		//System.out.println("state"+state);
		up.setUpTime(new Date());
		up.setUpUser(u.getUsername());	
		bs.addFile(up);
		System.out.println(up);
		FileUtils.copyFile(photo, file2);	
		ServletActionContext.getRequest().setAttribute("message", "上传成功！！！");
		return "upfile";
	}
	
	public  String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		System.out.println(dir);
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}
	
	
	public String MakeFilename(String Filename) {
		return UUID.randomUUID().toString() + "_" + Filename;
	}

}
