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

<!-- Load the CloudCarousel JavaScript file -->
<script type="text/JavaScript" src="js/cloud-carousel.1.0.5.js"></script>
</head>

<body id="subpage">

<div id="svw_header_wrapper">
	<%@ include file="cheader.jsp"%>
	
</div>	

<div id="svw_slider">
	<!-- This is the container for the carousel. -->
    <div id = "carousel1" style="width:960px; height:280px;background:none;overflow:scroll; margin-top: 20px">            
        <c:forEach items="${cHlist}" var="video">
	        <a href="${pageContext.request.contextPath}/video_findByVid?vid=${v.vid }" rel="lightbox">
	        	<img class="cloudcarousel" src="${video.thumbnailurl}" alt="${video.name}" title="${video.name}" /> 
	        </a>
        </c:forEach>
    </div>
    
    <!-- Define left and right buttons. -->
    <center>
    <input id="slider-left-but" type="button" value="" />
    <input id="slider-right-but" type="button" value="" />
    </center>
</div>

<div id="svw_main">
	<h2>~~~~一个大的旋转论~~~~</h2>
            <div class="post">
	            <div class="meta">
		            <span ><b><a href="">视频管理</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>添加</a></b></span>
	            </div> 
            </div>
            <!--  
            <c:if test="${empty resultvideo}">
	            <p><s:property value="%{getText('video.listempty')}"/></p>
	            <div style="height:300px;"></div>
            </c:if>
            -->
            <!-- 
            <c:forEach items="${pageBean.list }" var="video">
				<a href="#" rel="lightbox">
	        	<img class="cloudcarousel" src="${video.thumbnailurl}" alt="${video.name}" title="${video.name}" /> 
	        </a>
			</c:forEach>
			 -->
            <c:forEach items="${pageBean.list}" var="video">
				<div class="col one_fourth gallery_box" style="${video.videoState.cssstyle}">
	                <a href="${pageContext.request.contextPath}/video_findByVid?vid=${video.vid }"><img src="${video.thumbnailurl}" alt="${video.name}" class="image_frame"/></a>
	                <h5><a href="${pageContext.request.contextPath}/video_findByVid?vid=${video.vid }">${video.name}</a></h5>
	                <p>上传时间:${video.uploadtime}</p>
	                <p>
	                <a href="${pageContext.request.contextPath}/video_findByVid?vid=${video.vid }">替换</a>|
	                <a href="VideoUpdateRead.action?videoid=${video.vid}">替换</a>|
	                <a href="javascript:if(confirm('Are you sure to Delete?'))location='video_deleteByVid?vid=${video.vid}&cid=${cid}'">删除</a></p>
				</div>
            </c:forEach>
            
        <div class="cleaner"></div>
    	<span>第${pageBean.page}/${pageBean.totalPage}页</span>
    	
    	<c:if test="${pageBean.page!=1 }">
    		<a href="${pageContext.request.contextPath }/video_findByCid?cid=${cid}&page=${pageBean.page-1}">上一页</a>
    	</c:if>
    	
    	<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
    		<c:choose>
    			<c:when test="${pageBean.page!= i}">
    				<a href="${pageContext.request.contextPath }/video_findByCid?cid=${cid}&page=${i}">${i}</a>
    			</c:when>
    			<c:otherwise>
    				<span class="currentPage">${i}</span>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
    	
    	<c:if test="${pageBean.page!=pageBean.totalPage }">
    		<a href="${pageContext.request.contextPath }/video_findByCid?cid=${cid}&page=${pageBean.page+1}">下一页</a>
    	</c:if>
    	
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