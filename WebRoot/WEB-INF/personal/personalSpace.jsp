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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/others.css">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/qq.js"></script>
<script type="text/javascript"
	src="http://mat1.gtimg.com/ilike/js/ilike-1226-min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="http://mat1.gtimg.com/www/core/core_v1.5.1.js"></script>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script type="text/javascript">
	window.onload = function() {
		tick();
		trcolor();
	};
	function trcolor() {
		var tabNode = document.getElementById("spaceUL");
		var trNode = tabNode.getElementsByTagName("li");
		var name;
		for (var i = 0; i < trNode.length; i++) {
			if (i % 2 == 0) {
				trNode[i].className = "one";
			} else {
				trNode[i].className = "two";
			}
			trNode[i].onmouseover = function() {
				name = this.className;
				this.className = "over";
			};
			trNode[i].onmouseout = function() {
				this.className = name;
			};
		}
	}
</script>

<style>
.over {
	margin: 0;
	padding: 0;
	line-height: 1.7em;
	font-family: Verdana, Geneva, sans-serif;
	font-size: 12px;
	background-color:;
	border: 1px black solid;
}

.one {
	border: 1px black solid;
	background: white;
}

.two {
	border: 1px black solid;
	background: white;
}

div {
/*	border: 1px red solid;
	*/
}
</style>
</head>
<body style="text-align: center;">

	<div class="main">
		<div class="header" style="height: 100px">
			<div class="header_resize"
				style="height:100px;border: 1px black solid;">
				<div class="logo">
					<h1>
						<a href="index.do"> LC <span> .STUDIO </span> <small>
								Straight masterpieces </small>
						</a>
					</h1>
				</div>

				<div class="menu_nav">
					<ul>
						<li class="active"><a
							href="${pageContext.request.contextPath}/index.do">主页</a></li>
						<li><a
							href="${pageContext.request.contextPath}/personalSpace.do">个人中心</a></li>
						<li><a href="#">文件上传记录</a></li>
						<li><a href="#">游戏娱乐</a></li>
						<li><a href="#">最新公告</a></li>
						<li><a href="#">随便啦</a></li>
					</ul>
				</div>

				<!-- 时间 -->
				<div class="time">
					<span class="t" style="" id="localtime"></span>
					<div style="float:right;margin-right:20px;margin-top: 9px;">
						<c:if test="${user!=null}">
							<b style="color: black;font-size: 12px;">WELCOME :</b>
								${user.name} &nbsp;<a
								href="${pageContext.request.contextPath}/logout.do">注销</a>
						</c:if>
					</div>

				</div>
				<!-- 图片 -->

				<!-- 	<div class="picture" style="height:50px;" id="person">
					<c:if test="${user.image!=null}">
						 <div class="headPhoto">
							<img
								style="height: 100px;width: 80px;float: left; border-radius:;"
								alt="^-^" src="${pageContext.request.contextPath}/headPhoto">						
							<div class="clear"></div>
							
						</div>
			
					</c:if>
				</div>-->
			</div>
		</div>

		<div style="clear: both;"></div>
		<div class="content_resize" style="height: ;">
			<div style="clear: both;"></div>
			<div class="menu_nav"
				style="height: 40px;float: left;margin-left:80px;margin-top: 1px;">
				<ul id="spaceUL" style="float: left;margin-top: 1px;">
					<li><a
						href="${pageContext.request.contextPath}/personalSpace.do">我的文章</a></li>
					<li><a href="${pageContext.request.contextPath}/fileCenter.do">文件中心</a></li>
					<li><a href="${pageContext.request.contextPath}/publicNotice.do">发布公告</a></li>
					<li><a href="${pageContext.request.contextPath}/changeData_toPage.do">资料修改</a></li>
				</ul>
			</div>

			<div style="clear: both;"></div>
			<div class="layoutA" style="margin-top:5px;height: 178px;">
				<div class="topBarBg" id="topBarBg">
					<div class="bg">
						<img src="" />
					</div>
				</div>
				<div class="picture"
						style="position:absolute;height:50px; top: 30px;left:80px; width: 120px;"
						id="person">
						<c:if test="${user.image!=null}">
							<div class="headPhoto">
								<img
									style="height: 100px;width: 80px;float: left; border-radius:50%;"
									alt="^-^" src="${pageContext.request.contextPath}/headPhoto">
								<div class="clear"></div>

							</div>

						</c:if>
					</div>
				<div class="mainBox clear" style="height: 178px;width:500px;float:right ;">
					
					<div class="right" style="float:right;margin-bottom: 50px;margin-left: 100px;">
						<div class="weather_mod" style="height: 118px;">

							<div class="weatherBox clear">
								<div class="weatherDate">
									<span><span id="wMonth"></span>-<span id="wDate"></span></span>&nbsp;<span
										id="wDay"></span>
								</div>
								<div
									style="width: 50px;position: absolute;left:100px; top: 73px;">
									欢迎你</div>
							</div>
							<div class="tipsTxt" id="tipsTxt"
								style="position:absolute;right: 60px;"></div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript"
				src="http://mat1.gtimg.com/ilike/js/ilike-1226-min.js"
				charset="utf-8"></script>
				<div style="border: 1px black solid;height: 300px;">
				  <c:if test="${articles==null}">
				    <b style="color: black; font-size: 12px;">你还没有写过文章 </b><br>
				    <img src="${pageContext.request.contextPath}/image/article.gif">
				  </c:if>
				  
				  <c:if test="${articles!=null}">
				    <table width="500px" align="center" > 
				       <tr>
				         <td></td>
				         <td>文章名称</td>
				         <td>时间</td>
				         <td>作者</td>
				         <td></td>
				       </tr>
				       <c:forEach var="article" varStatus="index" items="${articles}">
				        <tr>
				        <td>${index.index+1}</td>
				         <td>${article.title}</td>
				         <td>${article.time}</td>
				         <td>${article.username}</td>
				         <td>
				          <a href="${pageContext.request.contextPath}/toArticlePage.do?articleId=${article.id}">查看</a>
				          <a href="${pageContext.request.contextPath}/deleteArticle.do?articleId=${article.id}">删除</a>
				         </td>
				        </tr>
				       </c:forEach>
				    </table>				    
				    <br><br><br>				    
				  </c:if>
				  <br>
				    <a href="${pageContext.request.contextPath}/toWritePage.do" style="color: "><b style="font-size: 15px;">>>>>>>>写文章<<<<<<<</b></a>
				</div>
				<div  style="clear: both;"></div>
				
		</div>
		<div  style="clear: both;"></div>
		<div class="footer">
			<div class="footer_resize">
				<p class="lf">
					&copy; Copyright <a href="#"></a>罗超
				</p>
				<hr>
				<div class="menu_nav" style="width:530px; ">

					<ul>
						<li class="active"><a
							href="${pageContext.request.contextPath}/index.do">主页</a></li>
						<li><a
							href="${pageContext.request.contextPath}/personalSpace.do">个人中心</a></li>
						<li><a href="${pageContext.request.contextPath}/fileUpRecord.do">文件上传记录</a></li>
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
