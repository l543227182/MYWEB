package cn.lc.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import cn.lc.domain.UPFile;
import cn.lc.service.imp.BusinessServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class deleteFileAction extends ActionSupport {
    private BusinessServiceImpl bs=new BusinessServiceImpl();
    private String fileId;
    
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public String execute() throws Exception {
		
		UPFile file=bs.findFile_id(fileId);
		System.out.println(file);
		String path="C:\\Users\\Administrator\\Desktop\\文件\\JavaupFile";
		
		path=makePath(file.getPath(), path);
		File delFile=new File(path,file.getPath());
		System.out.println(path);
		if(delFile.exists())
		{
			delFile.delete();
		}
		bs.deleteFile(fileId);
		ServletActionContext.getRequest().setAttribute("message", "删除成功");
		return SUCCESS;
	}
     public String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		System.out.println(dir);
	
		return dir;
	}
}
