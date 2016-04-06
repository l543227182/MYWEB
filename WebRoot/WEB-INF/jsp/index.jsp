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
<link type="text/css" href="css/index.css" rel="Stylesheet" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript" src="js/time.js"></script>
<script type="text/javascript">
	window.onload = function() {
		tick();
	};
</script>
<style>
div {
		border: 1px white solid;
	
}
</style>
</head>
<body style="text-align: center;">
	<!-- 主体 -->
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
				<div class="mainbar">

					<div class="article">

						<div>						
							<c:forEach var="s" items="${str}">
								<div class="kb_item">
									${s}
									<div class="clear"></div>
								</div>
							</c:forEach>
						</div>
						<!-- 
添加爬取的网页
 -->
						<div class="clear"></div>
					</div>
				</div>

				<div class="sidebar">

					<div class="gadget">

						<form action="http://www.baidu.com/baidu" target="_blank">
							<input name="tn" type="hidden" value="baidu">
							<table bgcolor="#FFFFFF">
								<tr>
									<td><a href="http://www.baidu.com/"><img
											src="http://img.baidu.com/img/logo-80px.gif" alt="Baidu"
											align="bottom" border="0"></a> <input type="text"
										name="word" size="30" baiduSug="1"> <input
										type="submit" value="百度搜索"></td>
								</tr>
							</table>
						</form>

												
						<b style="color: green; font-size: 18px">最新公告</b>
						
						<div class="clr"></div>
						<ul class="sb_menu">
						   <c:forEach var="notice"  items="${notices}">
						     <li><a href="${pageContext.request.contextPath}\NoticePage.do?noticeId=${notice.id}">${notice.title}</a></li>
						   </c:forEach>
						</ul>
					</div>
					<div class="gadget">
						<b style="font-size: 18px;color: green;">文件下载</b>
						<ul class="ex_menu">
							<c:forEach var="file" items="${files}">
							 <li><a href="${pageContext.request.contextPath}/filedown.do?fileName=${file.path}">${file.fileName}</a></li>
							</c:forEach>
							<li><a href="${pageContext.request.contextPath}/fileUpRecord.do">>>查看更多<<</a></li>
						</ul>
					</div>
				</div>
				<div class="clr"></div>
			</div>
		</div>


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
