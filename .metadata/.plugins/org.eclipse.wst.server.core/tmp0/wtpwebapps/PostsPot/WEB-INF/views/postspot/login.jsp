<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PostsPot-login</title>

<link rel="stylesheet" type="text/css" href="/postspot/resources/css/login.css"/>

<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
		str = f.id.value;
		str = str.trim();

		if(!str){
			alert("아이디를 입력하세요!");
			f.id.focus();
			return;
		}
		f.id.value=str;
		
		str = f.pwd.value;
		str = str.trim();
		if(!str){
			alert("비밀번호를 입력하세요!");
			f.pwd.focus();
			return;
		}
		f.pwd.value=str;
		
		f.action = "<%=cp%>/login_ok";
		f.submit();
	
	}
	
</script>

</head>
<body>


<div class=container>

	<div id="header_title">
		PostsPot - 로그인
	</div>

<form action="" name="myForm" method="post">
	<div id="login_area">
		<div id="login_content">
			<label>아이디</label>
			<br/>
				<input type="text" name="id" id="login_inputbox">
			<br/>
		</div>
		<div id="login_content">
			<label>비밀번호</label>
			<br/>
				<input type="password" name="pwd" id="login_inputbox">
			<br/>
		</div>			
	
	<div id="login_btnArea">	
		<button type="button" id="cancel_btn" onclick="javascript:location.href='<%=cp%>/mainHome'">취소</a></button>
		<button type="button" id="login_btn" onclick="sendIt();">완료</button>
	</div>
	
</form>	
	
	<div>
		<button type="button" id="account_btn" onclick="javascript:location.href='<%=cp%>/createAccount'">회원가입</button>
	</div>
	</div>


</div>







</body>
</html>