<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sitemesh" tagdir="/WEB-INF/tags/sitemesh" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<!-- write.tag덕분에 에러 안남. -->
	<title><sitemesh:write property="title"/></title><!-- 합치고, 한곳에서 관리가능 -->
	
	<link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css">
	<link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.css">
	<link rel="stylesheet" href="/webjars/animate.css/3.5.2/animate.css">
		
	<script type="text/javascript" src="/webjars/jquery/1.11.1/jquery.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
	<script type="text/javascript" src="/webjars/chartjs/2.6.0/Chart.bundle.js"></script>

	<sitemesh:write property="head"/>
	
	<!-- content assist를 위함. -->
	<c:if test="${false}">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" href="../css/animate.css">
	</c:if>
	
</head>
<body calss="container">
	<header class="page-header">
		Header
	</header>
	
	<section class="panel panel-default">
			<sitemesh:write property="body"/> <!-- 데코대상의 body가 들어옴 -->
	</section>
	
	<footer>Footer</footer>
</body>
</html>