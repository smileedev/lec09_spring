<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href='<c:url value="/resources/css/include/nav.css"/>' rel="stylesheet" type="text/css">
<style>
	#nav_wrap .menu form input[type='submit']{
	  border: 0;
	  background-color: transparent;
	  cursor : pointer;
	}
</style>
<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
			
				<!-- 인증되지 않은 유저만 접근 가능: isAnonymous() -->
				<sec:authorize access="isAnonymous()">
					<li>
						<a href="<c:url value='/loginPage'/>">로그인</a>
					</li>
					<li>
						<a href="<c:url value='/join'/>">회원가입</a>
					</li>
				</sec:authorize>
				
				
				<!-- 인증된 유저만 접근 가능: isAuthenticated() -->
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value='/chattingPage'/>">채팅하기</a>
					</li>
					<li>
						<sec:authentication property="principal.member.user_name"/>
					</li>
					<li>
						<form method="post" action="/logout">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="submit" value="로그아웃">
						</form>
					</li>
				</sec:authorize>
			</ul>
		</div>
		<div class="search">
			<form action="<c:url value='/board'/>" method="get">
				<!-- <input type="text" name="board_title" placeholder="검색어를 입력하세요."> -->
				<select name="search_type">
					<option value="1" <c:if test="${paging.search_type == '1'}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${paging.search_type == '2'}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${paging.search_type == '3'}">selected</c:if>>제목+내용</option>
				</select>
				<input type="text" name="search_text" placeholder="검색어를 입력하세요.">
				<input type="submit" value="검색">
			</form>
		</div>
	</div>
</nav>