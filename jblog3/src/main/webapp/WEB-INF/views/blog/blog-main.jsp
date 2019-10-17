<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
					<c:when test="${empty post }">
						<h4> 내용이 없습니다. </h4>
						<p>	내용이 없습니다. <p>
					</c:when>
					<c:otherwise>
						<h4>${post.title }</h4>
						<p>
							 ${post.contents }						
						<p>
					</c:otherwise>
				</c:choose>
				</div>
				<ul class="blog-list">
					<c:choose>
					<c:when test="${empty postList }">
						<li> 목록이 없습니다. </li>
					</c:when>
					<c:otherwise>
						<c:forEach var="post" items="${postList }">
						<li><a href="${pageContext.servletContext.contextPath }/${blogId }/${post.categoryNo }/${post.no }">${post.title}</a> <span>${post.regDate}</span>	</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
					
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${vo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="category" items="${categoryList }">
					<li><a href="${pageContext.servletContext.contextPath }/${blogId }/${category.no }">${category.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>