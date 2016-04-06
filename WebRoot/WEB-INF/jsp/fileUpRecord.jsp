<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>LC-studio</title>
<link rel="icon"
	href="C:\Users\Administrator\Desktop\html\纪念\images\img1.ico"
	type="image/x-icon">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/cufon-yui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/arial.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/cuf_run.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/time.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/birthday.js"></script>
<script type="text/javascript">
window.onload=function ()
{
	tick();
	 pageInit(); 
};
function disable(a)
{
	// a.disable="ture";	 
}	
</script>
<style>
div {
	/*
 border: 1px red solid;
*/
	
}
</style>
</head>
<body>

	<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<h1>
						<a href="index.do"> LC <span> .STUDIO </span> <small>
								Straight masterpieces </small>
						</a>
					</h1>
				</div>
				<div class="menu_nav">
					<ul>
						<li class="active"><a href="index.do">主页</a></li>
						<li><a
							href="${pageContext.request.contextPath}/personalSpace.do">个人中心</a></li>
						<li><a href="${pageContext.request.contextPath}/fileUpRecord.do">文件上传记录</a></li>
						<li><a href="#">游戏娱乐</a></li>
						<li><a href="#">最新公告</a></li>
						<li><a href="#">随便啦</a></li>
					</ul>
				</div>
				<!-- 时间 -->
				<div class="clear"></div>
				<div class="time">
					<span class="t" style="" id="localtime"></span>
					<div style="float:right;margin-right:20px;margin-top: 7px;">
						<c:if test="${user==null}">
							<s:form action="login" method="post">
								<s:token />
								<b style="color: black;">用户名:</b>
								<s:textfield label="用户名" name="username" />
								<b style="color: black;">密码:</b>
								<s:password label="密码" name="password" />
								<s:submit value="登录" />
								<b style="color: black;">还没注册?这边</b>
								<a href="${pageContext.request.contextPath}/for/register.jsp">
									注册</a>
							</s:form>
							<s:actionerror></s:actionerror>
						</c:if>
						<c:if test="${user!=null}">
							<div style="margin-top: 12px;">
								<b style="color: black;font-size: 12px;">WELCOME :</b>
								${user.name} &nbsp;<a
									href="${pageContext.request.contextPath}/logout.do">注销</a>
							</div>
						</c:if>
					</div>
				</div>
				<!-- 图片 -->
				<div class="picture">
					<img style="margin-bottom: 10px" alt="^-^" src="image/1.png">
				</div>
			</div>
		</div>

	<div class="content">
		<div class="content_resize">
			<div class="mainbar" align="center">
			<br>
				   <table align="center" style="width:550px;margin-top: 10px;margin-left: 100px ;font-size: 5px;">
				          <tr style="margin-top: 10px;">
				            <th></th>
				            <th>文件名</th>				       
				            <th>上传日期</th>
				            <th>文件大小</th>
				            <th>状态</th>
				            <th>上传用户</th>
				            <th>操作</th>
				          </tr>
				          <c:forEach varStatus="s" var="file" items="${page.list}">
				             <tr style="margin-top: 10px;">
				            <th>${s.index+(page.pageNum-1)*10+1}.</th>
				            <th>${file.fileName}</th>				       
				            <th>${file.upTime}</th>				            
				            <th>${file.fileSize}kb</th>
				            <th>
				            <c:if test="${file.state==true}">
				                                            公开
				            </c:if>
				            <c:if test="${file.state==false}">
				                                        个人
				            </c:if>
				            </th>
				            <th>${file.upUser}</th>
				            <th>
				          
				            <c:if test="${file.state==true}">
				               <a href="${pageContext.request.contextPath}/filedown.do?fileName=${file.path}">下载</a>	                        
				            </c:if>	
				             <c:if test="${file.state==false}">
				               <a href="#">联系所有人</a>	                        
				            </c:if>				           				         
				            </th>
				          </tr>
				          </c:forEach>
				          </table>
				             <c:if test="${0<page.pageNum-1}">
				             <a href="${pageContext.request.contextPath}\fileUpRecord.do?pageNum=${page.pageNum-1}">上一页</a>
				             </c:if>
				            
				             >>第${page.pageNum}页<< 
				           
				             <c:if test="${page.totalPage>=page.pageNum+1}">
				             <a href="${pageContext.request.contextPath}\fileUpRecord.do?pageNum=${page.pageNum+1}">下一页</a>
				             </c:if>
			</div>
			<div class="clr"></div>
		</div>
	</div>

	<div class="clr"></div>

	<div class="footer">
		<div class="footer_resize">
			<p class="lf">
				&copy; Copyright <a href="#"></a>罗超
			</p>
			<hr>
			<div class="menu_nav">
				<ul>
					<li class="active"><a href="index.do">主页</a></li>
					<li><a
						href="${pageContext.request.contextPath}/personalSpace.do">个人中心</a></li>
					<li><a href="#">文件上传记录</a></li>
					<li><a href="#">游戏娱乐</a></li>
					<li><a href="#">最新公告</a></li>
					<li><a href="#">随便啦</a></li>

				</ul>
			</div>



			<div class="clr"></div>
		</div>
	</div>
</div>
</body>
</html>
