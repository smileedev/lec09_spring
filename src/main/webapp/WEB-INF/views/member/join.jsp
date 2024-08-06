<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="<c:url value='/resources/css/member/join.css' />" rel="stylesheet" type="text/css">
</head>
<body>	
	<jsp:include page="../include/header.jsp"/>
	<jsp:include page="../include/nav.jsp"/>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>회원가입</h3>
			</div><br>
			<div class="create_account_form">
				<form id="memberAddFrm">
					<input type="text" name="user_id" placeholder="아이디"> <br>
					<input type="password" name="user_pw" placeholder="비밀번호"> <br>
					<input type="password" name="user_pw_check" placeholder="비밀번호 확인"> <br>
					<input type="text" name="user_name" placeholder="이름"> <br>
					<input type="submit" value="회원가입"> 
				</form>
			</div>
			<div class="login">
				<a href="<c:url value='/loginPage' />">로그인</a>
			</div>
		</div>
	</section>	
	<script>
		const form = document.getElementById('memberAddFrm');
		
		form.addEventListener('submit', (e)=>{
			e.preventDefault();
			
		let object = {};
		const payload = new FormData(form);
		payload.forEach(function(value,key){
			object[key] = value;
		});
		
		const jsonData = JSON.stringify(object);
		
			fetch('<%=request.getContextPath()%>/join',{
				method : 'POST',
				headers : {
					"Content-Type" : "application/json;charset=utf-8",
					"Accept" : "application/json",
					"X-CSRF-TOKEN" : "${_csrf.token}"
				},
				body : jsonData
			})
			.then(response => response.json())
			.then(data =>{
				alert(data.res_msg);
				if(data.res_code == '200'){
					location.href="<%=request.getContextPath()%>/loginPage";
				}
			})
		});
	</script>
</body>
</html>