<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="header">
		<h1>Spring 이야기</h1>
			<ul>
				<c:choose>
					<c:when test='${empty authUser }'>
						<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath }/${authUser.id }/blog-admin-basic">블로그관리</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
						<li>${authUser.name }의 블로그 ^___^;;</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>