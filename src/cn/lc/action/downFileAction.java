package cn.lc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.lc.domain.UPFile;
import cn.lc.domain.User;
import cn.lc.service.imp.BusinessServiceImpl;
import cn.lc.utils.Webutils;

import com.opensymphony.xwork2.ActionSupport;

public class downFileAction extends ActionSupport {		
	private String fileId;
	private InputStream image;
	private long fileSize;
	private String fileName;
	private BusinessServiceImpl bs=new BusinessServiceImpl();
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
	 //下载文件
	public String downFile() throws  Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
	    User u=(User) request.getSession().getAttribute("user");
		String path="C:\\Users\\Administrator\\Desktop\\文件\\JavaupFile";
		fileName=new String(fileName.getBytes("iso8859-1"),request.getCharacterEncoding());
		
		System.out.println("下载文件名字:"+fileName+"     "+"request编码："+request.getCharacterEncoding());
		path=makePath(fileName, path);
		
		System.out.println("下载文件路径:"+path);
		image=new FileInputStream(path+"\\"+fileName);
		
		fileSize=new File(path+"\\"+fileName).length();
		fileName=fileName.substring(fileName.lastIndexOf("_")+1);
		fileName = URLEncoder.encode(fileName, "UTF-8");
		return "downFile";
	}
	
	public String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}
	
	public String MakeFilename(String Filename) {
		return UUID.randomUUID().toString() + "_" + Filename;
	}

	public String CreateFileName(String filename, String SaveName) {
		int dir1 = filename.hashCode() & 0xf;
		int dir2 = (filename.hashCode() & 0xf0) >> 4;

		String dir = SaveName + "\\" + dir1 + "\\" + dir2;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}
	
	
	//列出文件
	public void GetAllName(File file, Map map) {
		if (!file.isFile()) {

			File[] files = file.listFiles();
			for (File f : files) {
				GetAllName(f, map);
			}
		} 
		else {
          String filename=file.getName();
         
          filename=filename.substring(filename.lastIndexOf("_")+1); 
          System.out.println(filename);
          map.put(file.getName(), filename);
		}
	}
}
