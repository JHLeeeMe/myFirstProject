<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>

<c:if test="${false}">
<link rel="stylesheet" href="../../css/bootstrap.css">
<link rel="stylesheet" href="../../css/animate.css">
</c:if>
</head>
<body>
	<div class="panel-heading">View Page</div>
	<div class="panel-body">

		<div class="form-group">
			<label>ID</label> 
			<input class="form-control" name="id" value="${vo.id}" readonly="readonly" />
		</div>

		<div class="form-group">
			<label>Name</label> 
			<input class="form-control" name="name" value="${vo.name}" readonly="readonly" />
		</div>

		<div class="form-group">
			<label>District</label>
			<textarea class="form-control" name='district' readonly="readonly">${vo.district}</textarea>
		</div>
		
		<div class="form-group">
			<label>Population</label> 
			<input class="form-control" name="population" value="${vo.population}" readonly="readonly" />
		</div>
		
		<div class="form-group">
			<label>CountryCode</label> 
			<input class="form-control" name="country" value="${vo.country.code}" readonly="readonly" />
		</div>

		<div class="pull-right">
			<a href="/city/modify?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&id=${vo.id}" class="btn btn-default">Modify/Delete</a> 
			<a href="/city/list?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&id=${vo.id}" class="btn btn-primary">Go List</a> 
		</div>

	</div>

</body>
</html>

