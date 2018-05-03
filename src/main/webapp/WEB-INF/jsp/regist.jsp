<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>

<script>
	function checkForm(){
		//校验用户名
		//获得用户名文本框值
		var username = document.getElementById("username").value;
		if(username == null || username==''){
			alert("用户名不能为空!");
			return false;//这样表单不会提交
		}
		
		//校验密码
		//获得密码文本框值
		var password = document.getElementById("password").value;
		if(password == null || password ==''){
			alert("密码不能为空!");
			return false;//这样表单不会提交
		}
		//校验确认密码
		var repassword = document.getElementById("repassword").value;
		if(repassword != password) {
			alert("两次密码输入不一致");
			return false;
		}
	}
	//ajax异步校验
	function checkUsername(){
		//获得文本框的值
		var username = document.getElementById("username").value;
		// 1 创建异步交互对象
		var xhr = createXmlHttp();
		// 2 设置监听
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) { //表示后台处理完成
				if(xhr.status == 200) {  //表示后台处理的结果是ok
					document.getElementById("span1").innerHTML = xhr.responseText;
				}
			}
		}
		// 3 打开链接
		//这样极其容易引起浏览器的缓存，因为浏览器的路径不变，加时间戳解决
		xhr.open("GET","${pageContext.request.contextPath}/user_findByUserName?time="+new Date().getTime()+"&username="+username,true);		
		//4 发送
		xhr.send(null);
	}
	
	function createXmlHttp(){
		var xmlHttp;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch(e) {
			try {
				xmlHttp = new ActiveObject("Msxml2.XMLHttp");
			} catch(e) {
				try {
					xmlHttp = new ActiveObject("Microsoft.XMLHttp");
				} catch(e) {}
			}
		}
		return xmlHttp;
	}
	
	function change(){
		var img1 = document.getElementById("checkImg");
		//加时间戳,解决缓存问题
		img1.src = "${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	}
</script>

</head>
<body>
	<form action="${pageContext.request.contextPath }/regist" method="post" onsubmit="return checkForm();">
		<table>
			<tbody>
				<tr>
					<th>
						<span class="requiredField">*</span>用户名:
					</th>
						<td>
							<input type="text" id="username" name="username" class="text" maxlength="20" onblur="checkUsername()"/>
							<span id="span1"></span>
						</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>密&nbsp;&nbsp;码:
					</th>
					<td>
						<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>确认密码:
					</th>
					<td>
						<input type="password" id="repassword" name="repassword" class="text" maxlength="20" autocomplete="off">
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>E-mail:
					</th>
					<td>
						<input type="text" id="email" name="email" class="text" maxlength="200">
						<span><s:fielderror fieldName="email"/></span>
					</td>
				</tr>
				<tr>
					<th>
						姓名:
					</th>
					<td>
						<input type="text" name="name" class="text" maxlength="200">
					</td>
				</tr>
				<tr>
					<th>
						电话号码:
					</th>
					<td>
						<input type="text" name="phone" class="text" maxlength="200">
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>验证码:
					</th>
					<td>
						<span class="fieldSet">
							<input type="text" id="checkcode" name="checkcode" class="text captcha" maxlength="4" autocomplete="off"><img id="checkImg" class="captchaImage" src="${pageContext.request.contextPath}/checkImg" onclick="change()" title="点击更换验证码">
							${requestScope.codeError }.....
						</span>
					</td>
				</tr>
				<tr>
					<th>&nbsp;
					</th>
					<td>
						<input type="submit" class="submit" value="注册">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	
</body>
</html>