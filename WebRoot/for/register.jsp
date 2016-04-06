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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script type="text/javascript">
	function createXMLHttpRequest() {
		try {
			return new XMLHttpRequest();
		} catch (e) {
			try {
				return new ActvieXObject("Msxml2.XMLHTTP");
			} catch (e) {

				try {
					return new ActvieXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("卧槽 您用的什么浏览器啊!!!");
					throw e;
				}
			}
		}
	}
	window.onload = function() {

		{
			var eleUser = document.getElementById("username");
			eleUser.onblur = function() {
				var xml = createXMLHttpRequest();
				xml.open("POST","<c:url value='${pageContext.request.contextPath}/MYWEB/findUser.do'/>",true);
				//3.设置请求头：Content-Type
				xml.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");
				//4.发送请求，给出请求体
				xml.send("username=" + eleUser.value);
				
				xml.onreadystatechange = function() {
					if (xml.readyState == 4 && xml.status == 200) {
						var text = xml.responseText;
						var span = document.getElementById("errorspan");
						// alert(text);
						if (text == "1") {
							//  alert("用户名被注册");
							span.innerText = "用户名被注册";
						} else {
							//alert("用户名么有注册");
							span.innerText = "";
						}
					}
				};
			};
		}
		;

		tick();
		pageInit();
	};
</script>
<style>
div {
	/*border: 1px red solid;*/
	
}
</style>
</head>
<body style="margin-left:auto;margin-right:auto;  text-align:center;">

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
				<!--  
<div class="time">
  <span  class="t" id="localtime"></span>
</div>

  <div>
   <img style="margin-top:40px;" alt="^-^" src="../image/1.png">
   </div>
-->
				<div class="time">
					<span class="t" id="localtime"></span>
				</div>
				<div class="picture">
					<img style="margin-bottom: 30px" alt="^-^"
						src="${pageContext.request.contextPath}/image/1.png">
				</div>
			</div>

		</div>
	</div>

	<div class="content">
		<div class="content_resize">
			<div class="mainbar">
				<div class="article">
					<br>
					<hr>
					<br>
					<div>
						<s:form action="register" method="post"
							onsubmit="return dosubmit()" id="register">
							<s:token></s:token>


							<b style="color: black;">姓名</b>
							<s:textfield name="user.name" label="user.name"></s:textfield>
							<br>
							<s:fielderror fieldName="user.name"></s:fielderror>
							<br>

							<b style="color: black;">年龄</b>
							<s:textfield name="user.age" label="user.age"></s:textfield>
							<br>
							<br>

							<b style="color: black;margin-right:76px;">性别</b>&nbsp;<s:radio
								list="#{'1':'男','2':'女'}" name="user.gender"></s:radio>
							<br>
							<s:fielderror fieldName="user.gender"></s:fielderror>
							<br>

							<b style="color: black;">用户名</b>
							<s:textfield name="user.username" label="user.username"
								id="username"></s:textfield>
							<s:fielderror fieldName="user.username"></s:fielderror>
							<br>
							<span id="errorspan"></span>
							<br>

							<b style="color: black;">密码</b>
							<s:password name="user.password" label="user.password"></s:password>
							<br>
							<s:fielderror fieldName="user.password"></s:fielderror>
							<br>

							<b style="color: black;">邮箱</b>
							<s:textfield name="user.email" label="user.email">
							</s:textfield>
							<br>
							<s:fielderror fieldName="user.email"></s:fielderror>
							<br>

							<b style="color: black;margin-left: 35px">生日</b>
							<select id="year">
							<option value="1900">1900</option></select>年
    			            <select id="month"><option value="01">01</option></select>月
    			            <select id="day"><option value="01">01</option></select>日
                            <br>
							<br>
							<b style="color: black;">电话</b>
							<s:textfield name="user.cellphone" label="user.cellphone"></s:textfield>
							<br>
							<s:fielderror fieldName="user.cellphone"></s:fielderror>
							<br>
							<s:submit value="注册">
							</s:submit>
							<s:reset value="重置"></s:reset>
						</s:form>
					</div>
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
					<h2 class="star">
						<span>Link&nbsp</span>to the memorial
					</h2>
					<div class="clr"></div>
					<ul class="sb_menu">
						<li><a
							href="http://yule.sohu.com/7/0304/39/column219543936.shtml">搜狐专题纪念</a></li>
						<li><a href="http://zgr.netor.com/">Nettor 纪念</a></li>
						<li><a href="http://www.eeloves.com/100044">forever
								Leslie</a></li>
						<li><a href="http://zhangguorong.yiqin.com/">忆亲网</a></li>
						<li><a
							href="http://his.tsingming.com/zhangguorong/#divWorksList">张国荣纪念馆</a></li>
						<li><a href="http://www.wasu.cn/Play/show/id/5776181">葬礼.绝版</a></li>
						<li><a href="#"></a></li>
					</ul>
				</div>
				<div class="gadget">
					<h2>Concert</h2>
					<ul class="ex_menu">
						<li><a
							href="http://www.iqiyi.com/v_19rrifuea9.html#vfrm=2-3-0-1">2000年热情演唱会</a><br /></li>
						<hr width="50%" align="center">
						<li><a href="http://www.iqiyi.com/w_19rr035kc5.html">2000拉阔演唱会</a><br /></li>
						<hr width="50%" align="center">
						<li><a
							href="http://www.iqiyi.com/yinyue/20120321/023181becc97faba.html#vfrm=2-3-0-1">1997年跨年演唱会</a><br /></li>
						<hr width="50%" align="center">
						<li><a
							href="http://www.iqiyi.com/yinyue/20120321/cdb6cf3c1b440b96.html">1989年告别演唱会</a><br /></li>
						<hr width="50%" align="center">
						<li><a href="http://www.56.com/u53/v_ODk3NTgyODI.html">1988年演唱会</a><br /></li>
						<hr width="50%" align="center">
						<li><a href="http://v.ku6.com/playlist/index_6592628.html">1985年演唱会</a><br /></li>
						<hr width="50%" align="center">
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
						<li class="active"><a href="${pageContext.request.contextPath}/index.do">主页</a></li>
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
</html>
