<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify.jsp</title>

<c:if test="${false}">
<link rel="stylesheet" href="../../css/bootstrap.css">
<link rel="stylesheet" href="../../css/animate.css">
</c:if>
</head>
<body>
	<div class="panel-heading">Modify Page</div>
	<div class="panel-body">

    <form id='f1'>

		<div class="form-group">
			<label>Code</label> 
			<input class="form-control" name="code" value="${vo.code}" readonly="readonly" />
			<p class="help-block">Code text here. ( PRIMARY KEY )</p>
		</div>

		<div class="form-group">
			<label>Capital</label> 
			<input class="form-control" name="capital" value="${vo.capital}" />
			<p class="help-block">Capital number here.</p>
		</div>

		<div class="form-group">
			<label>Code2</label>
			<textarea class="form-control"  name='code2'>${vo.code2}</textarea>
			<p class="help-block">Code2 text here.</p>
		</div>
		
		<div class="form-group">
			<label>Continent</label> 
			<input class="form-control" name="continent" value="${vo.continent}" />
			<p class="help-block">Continent text here.</p>
		</div>

		<div class="form-group">
			<label>Gnp</label>
			<input class="form-control" name="gnp" value="${vo.gnp}"/>
			<p class="help-block">Gnp number here.</p>
		</div>
		
		<div class="form-group">
			<label>GnpOld</label>
			<input class="form-control" name="gnpold" value="${vo.gnpold}"/>
			<p class="help-block">GnpOld number here.</p>
		</div>

		<div class="form-group">
			<label>GovernmentForm</label>
			<input class="form-control" name="governmentform" value="${vo.governmentform}"/>
			<p class="help-block">GovernmentForm text here.</p>
		</div>

		<div class="form-group">
			<label>HeadOfState</label>
			<input class="form-control" name="headofstate" value="${vo.headofstate}"/>
			<p class="help-block">HeadOfState text here.</p>
		</div>

		<div class="form-group">
			<label>LocalName</label>
			<input class="form-control" name="localname" value="${vo.localname}"/>
			<p class="help-block">LocalName text here.</p>
		</div>
		
		<div class="form-group">
			<label>Name</label>
			<input class="form-control" name="name" value="${vo.name}"/>
			<p class="help-block">Name text here.</p>
		</div>

		<div class="form-group">
			<label>LifeExpectancy</label>
			<input class="form-control" name="lifeexpectancy" value="${vo.lifeexpectancy}"/>
			<p class="help-block">LifeExpectancy number here.</p>
		</div>
		
		<div class="form-group">
			<label>Population</label>
			<input class="form-control" name="population" value="${vo.population}"/>
			<p class="help-block">Population number here.</p>
		</div>

		<div class="form-group">
			<label>Region</label>
			<input class="form-control" name="region" value="${vo.region}"/>
			<p class="help-block">Region text here.</p>
		</div>

		<div class="form-group">
			<label>SurfaceArea</label>
			<input class="form-control" name="surfacearea" value="${vo.surfacearea}"/>
			<p class="help-block">SurfaceArea number here.</p>
		</div>
		
		<input type='hidden' name="page" value="${pageVO.page}">
		<input type='hidden' name="size" value="${pageVO.size}">
		<input type='hidden' name="type" value="${pageVO.type}">
		<input type='hidden' name="keyword" value="${pageVO.keyword}">
		
		</form>

		<div class="pull-right">
		
		  <a href="#" class="btn btn-warning modbtn">Modify</a>
		  <a href="#" class="btn btn-danger delbtn">Delete</a>
		  <a href="/country/list?page=${pageVO.page}&size=${pageVO.size}&type=${pageVO.type}&keyword=${pageVO.keyword}&code=${country.code}" 
		  	  class="btn btn-primary">Cancel & Go List</a> 
		</div>

	</div>

	<script type="text/javascript">
	$(document).ready(function(){
	
		var formObj = $("#f1");
		
		$(".delbtn").click(function(){
			
			formObj.attr("action","delete");
			formObj.attr("method", "post");
			
			formObj.submit();
		});
		
		$(".modbtn").click(function(){
			
			formObj.attr("action","modify");
			formObj.attr("method", "post");
			
			formObj.submit();
		});
		
	});	
	</script>


</body>
</html>