<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>

	$(function() {
			var $submit = $("#submit");
			$submit.click(function() {
				
				var vo = {name : $("#name").val(), explain : $("#explain").val(), blogId : $("#id").val() };
				console.log(vo);
				
				// ajax 통신
				$.ajax({
					url: "${pageContext.servletContext.contextPath }/blogId/blog-admin-category",
					type: "post",
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(vo),
					success: function(response) {
						$("#name").val("");
						$("#explain").val("");
						$("#name").focus();
						alert("저장");
						location.reload();
					},
					error: function(xhr, error){
						console.error("error:"+error);
					}
				});
			});
	});
/* function deleteData(){
		$.ajax({
	        url : "${pageContext.servletContext.contextPath }/api/category/delete?no'"+categoryVo.no,
	        type : 'get',
	        dataType : 'json',
	        success : function(result){
	        	var str="";
	        	$.each(result, function(index, categoryVo){ 
		            str += "<tr>" +
		            "<td>" + eval(index+1) + "</td>" +
		            "<td>" + categoryVo.name + "</td>" +
		            "<td>" + categoryVo.explain + "</td>" +
		            "<td>" + "포스트 수" + "</td>" +
		            "<td>" +
		            "<a href='${pageContext.servletContext.contextPath }/api/category/delete?no'"+categoryVo.no
						+ ">"+ "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'" +
			            "class='delete-img'>" + "</a>"
		            "</td>" +
		            "</tr>";
		           });
		           $("#categoryList").append(str);
	        	},
	        
	        error : function(){
	            alert("error");
	        }
	    }) 
	}  */
	
	$('.dele').click(function(){
		var categoryNo = $('dele').val();
		console.log(categoryNo);
		$.ajax({
	        url : "${pageContext.servletContext.contextPath }/api/category/delete?no"+categoryNo,
	        type : 'get',
	        dataType : 'text',
	        success : function(result){
	        	location.reload();
	        },
			error : function(){
            	alert("error");
        	}
    })
});
	
	function createTable(){
		
	    $.ajax({
	        url : "${pageContext.servletContext.contextPath }/api/category/getList",
	        type : 'get',
	        dataType : 'json',
	        success : function(result){
	        	var str="";
	        	$.each(result, function(index, categoryVo){ 
		            str += "<tr>" +
		            "<td>" + eval(index+1) + "</td>" +
		            "<td>" + categoryVo.name + "</td>" +
		            "<td>" + categoryVo.explain + "</td>" +
		            "<td>" + "포스트 수" + "</td>" +
		            "<td>" +
		            "<button class='dele' value = '"+categoryVo.no+"'>" +
		            "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'" +
		            "class='delete-img'>" + 
		            "</button>"+
		            "</td>" +
		            "</tr>";
		           });
		           $("#categoryList").append(str);
		           //location.reload();
	        	},
	        
	        error : function(){
	            alert("error");
	        }
	    })
	}
	$(document).ready(function() {
		createTable();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/adminheader.jsp" />
		      	<table class="admin-cat" id="categoryList">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
									  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="explain"></td>
		      		</tr>
		      		<tr>
		      			<td><input type="hidden" id = "id" value='${vo.id }'></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="button" id = "submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>