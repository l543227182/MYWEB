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
		                     
			      ServletActionContext.getRequest().setAttribute("message", "�Բ������ϴ����ļ�̫�󣬷������ܾ����ܣ����ϴ�50M���µ��ļ���");
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
			ServletActionContext.getRequest().setAttribute("message", "�Ǻǣ��Ӹ��ļ���");
			return ERROR;
		}
		if(u==null){
			ServletActionContext.getRequest().setAttribute("message", "��¼��ע���������������");
			return ERROR;
		}
		if(photo.length()>51699500)
		{
			ServletActionContext.getRequest().setAttribute("message", "�ļ�̫���ˣ�ֻ����50M���£���");
			return ERROR;
		}
		if(!filename.isEmpty()){
			photoFileName=filename+"."+photoFileName.substring(photoFileName.lastIndexOf(".")+1);
		    System.out.println("���ǿյ�");	
		    up.setFileName(filename);
		}else{
			up.setFileName(photoFileName);
		}
		System.out.println(photoFileName+"     "+photoContentType);
		String path="C:\\Users\\Administrator\\Desktop\\�ļ�\\JavaupFile";		
		photoFileName=MakeFilename(photoFileName);
		path=makePath(photoFileName, path);
		System.out.println("����·����"+path+"\n����Ϊ:"+photoFileName);
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
		ServletActionContext.getRequest().setAttribute("message", "�ϴ��ɹ�������");
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
