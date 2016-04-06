package cn.lc.domain;

import java.util.List;

public class Page {

	private int totalPage;//总共多少页
	private int pageSize=10;//页面显示多少
	private int pageNum;//
	private int recordCount;//sql中有多少条记录
	private List list;
	private int sqlIndexStart;//数据库开始取得位置
	private int startPage;
	private int endPage;
	
	public Page(int pageNum,int recordCount) {
		// TODO Auto-generated constructor stub
		this.pageNum=pageNum;
		this.recordCount=recordCount;
		
		this.totalPage=(this.recordCount+this.pageSize-1)/pageSize;
		this.sqlIndexStart=(pageNum-1)*pageSize;
		if(totalPage<=10)
	    {
	    	startPage=1;
	    	this.endPage=this.totalPage;
	    }
	    else
	    {
	    	startPage=pageNum-4;
	    	endPage=pageNum+5;
	    	if(startPage<1){
	    		startPage=1;endPage=10;
	    		}
	    	if(endPage>totalPage)
	    	{
	    		endPage=totalPage;
	    		startPage=endPage-9;
	    	}
	    }
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getSqlIndexStart() {
		return sqlIndexStart;
	}

	public void setSqlIndexStart(int sqlIndexStart) {
		this.sqlIndexStart = sqlIndexStart;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
