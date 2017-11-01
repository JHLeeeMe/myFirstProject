<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>

<c:if test="${false}">
<link rel="stylesheet" href="../../css/bootstrap.css">
<link rel="stylesheet" href="../../css/animate.css">
</c:if>
</head>
<body>
	<div class="panel-heading">Register Page</div>
	<div class="panel-body">

		<form action="/city/register" method="post">

			<div class="form-group">
				<label>ID</label> 
				<input class="form-control" name="id" value="${vo.id}" />
				<p class="help-block">ID number here. ( PRIMARY KEY )</p>
			</div>

			<div class="form-group">
				<label>Name</label>
				<textarea class="form-control" name='name'>${vo.name}</textarea>
				<p class="help-block">Name text here.</p>
			</div>

			<div class="form-group">
				<label>District</label>
				<input class="form-control" name="district" value="${vo.district}" />
				<p class="help-block">District text here.</p>
			</div>
			
			<div class="form-group">
				<label>Population</label>
				<input class="form-control" name="population" value="${vo.population}" />
				<p class="help-block">Population number here.</p>
			</div>
			
			<div class="form-group">
				<label>CountryCode</label>
				<input class="form-control" name="country" value="${vo.country}" />
				<p class="help-block">CountryCode text here.</p>
			</div>
			
			<button type="submit" class="btn btn-default">Submit Button</button>
			<button type="reset" class="btn btn-primary">Reset Button</button>
		</form>

	</div>

</body>
</html>