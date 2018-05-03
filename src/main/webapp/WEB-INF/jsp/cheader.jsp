<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>

<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "svw_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
});

</script> 
	
	 <div align="right">
	 	<c:choose>
	 		<c:when test="${existUser==null }">
	 			 <h4><a href="${ pageContext.request.contextPath }/gotoLogin">登录</a>&nbsp;&nbsp;<a href="${ pageContext.request.contextPath }/gotoRegist">注册</a></h4>
	 		</c:when>
	 		<c:otherwise>
	 			 <h4>${existUser.username }&nbsp;&nbsp;<a href="${ pageContext.request.contextPath }/user_quit">退出</a></h4>
	 		</c:otherwise>
	 	</c:choose>
	 	
	 </div>
     <div id="svw_menu" class="ddsmoothmenu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index" >首页</a></li>
            <c:forEach items="${cList}" var="c">
            	<li><a href="${pageContext.request.contextPath }/video_findByCid?cid=${c.cid}&page=1">${c.name}</a></li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath }/video_downvideo?page=1">下载</a></li>
        </ul>
        <br style="clear: left" />
        
		<script type="text/javascript">
			$("#svw_menu>ul>li>a").click(function(){
				$("#svw_menu>ul>li>a").removeClass("selected");
				$(this).addClass("selected");
			});
		</script> 
    </div>
    <div class="cleaner"></div>