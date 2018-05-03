<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Video, FFmpeg, JavaEE" />
<meta name="author" content="Lei Xiaohua" />
<meta name="description" content="The simplest video website based on JavaEE and FFmpeg" />

<title>Simplest Video Website</title>

<link href="css/svw_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/showhide.js"></script> 
<link rel="stylesheet" href="css/slimbox2.css" type="text/css" media="screen" /> 
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 
<script type="text/JavaScript" src="js/slimbox2.js"></script> 

</head>

<body id="subpage">

<div id="svw_header_wrapper">
	<%@ include file="cheader.jsp"%>
</div>

<div id="svw_main">
	<h2>~~~~下载喽~~~~</h2>
           
	          <c:forEach items="${pageBean.list}" var="downvideo">
	             <div class="col one_fourth gallery_box">
		                <a href="${downvideo.dURL }"><img src="${downvideo.imageURL}" alt="${downvideo.title}" class="image_frame"/></a>
		                <h5><a href="${pageContext.request.contextPath}/video_findByVid?vid=${video.vid }">${video.title}</a></h5>
		                <p><a href="${downvideo.dURL}">${downvideo.title}</a></p>
				</div>
	          </c:forEach>
	          
	          <div>
    	<span>第${pageBean.page}/${pageBean.totalPage}页</span>
    	
    	<c:if test="${pageBean.page!=1 }">
    		<a href="${pageContext.request.contextPath }/video_downvideo?page=${pageBean.page-1}">上一页</a>
    	</c:if>
    	
    	<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
    		<c:choose>
    			<c:when test="${pageBean.page!= i}">
    				<a href="${pageContext.request.contextPath }/video_downvideo?&page=${i}">${i}</a>
    			</c:when>
    			<c:otherwise>
    				<span class="currentPage">${i}</span>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
    	
    	<c:if test="${pageBean.page!=pageBean.totalPage }">
    		<a href="${pageContext.request.contextPath }/video_downvideo?page=${pageBean.page+1}">下一页</a>
    	</c:if>
    </div>
            <div class="cleaner"></div>
            
    <div class="cleaner"></div>
</div> <!-- END of svw_main -->

 <!-- END of svw_bottom_wrapper -->

<div id="svw_footer_wrapper">
    <%@ include file="cfooter.jsp"%>
</div> <!-- END of svw_footer -->

        <script type="text/javascript">
</script> 

</body>
</html>