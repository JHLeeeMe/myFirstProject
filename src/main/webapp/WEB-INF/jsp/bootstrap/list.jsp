<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8>
	<title>list.jsp</title>

	<!-- for content assist -->
	<c:if test="${false}">
		<link rel="stylesheet" href="../../css/bootstrap.css">
		<link rel="stylesheet" href="../../css/animate.css">
	</c:if>
</head>
<body>
	<div class="panel-heading">
		<h1 class="animated fadeInRight">Bootstrap list Page</h1>
	</div>
	
	<div class="panel-body">
		<h3><a href="/bootstrap/register">Resister</a></h3>
	</div>
	
	<div class="panel-body">
		<table class="table table-bordered">
			<tr>
				<th>bno</th>
				<th>title</th>
				<th>writer</th>
			</tr>
				<c:forEach var="bno" begin="100" end="110">
			<tr>
					<td>${bno}</td><td><a href="${bno}">xxx ${bno}</a></td><td>user01</td>
			</tr>
				</c:forEach>
		</table>
		<div>
			<select id="typeSelect">
				<option value="t">Title</option>
				<option value="c">Content</option>
				<option value="w">Writer</option>
			</select>
			<input id="xxx" type="text">
			<button id="btnSearch">Search</button>
		</div>
	</div>
	<nav>
		<ul class="pagination">
			<li class="">			<a href="10">Prev 10</a></li>
			<li class="">			<a href="11">11</a></li>
			<li class="">			<a href="12">12</a></li>
			<li class="">			<a href="13">13</a></li>
			<li class="">			<a href="14">14</a></li>
			<li class="active">	<a href="15">15</a></li>	<!-- 현재 15Page를 보고있다.active -->
			<li class="">			<a href="16">16</a></li>
			<li class="">			<a href="17">17</a></li>
			<li class="">			<a href="18">18</a></li>
			<li class="">			<a href="19">19</a></li>
			<li class="">			<a href="20">20</a></li>
			<li class="">			<a href="21">Next 21</a></li>
		</ul>
	</nav>
	
	<!-- submit 없음 type이 hidden 프로그램상에서 /bootstrap/list에 value 다 넘어감. -->
	<form id="f1" action="/bootstrap/list" method="get">
		<input type="hidden" name="page" value="${pageVO.page}">
		<input type="hidden" name="size" value="${pageVO.size}">
		<input type="hidden" name="type" value="${pageVO.type}">
		<input type="hidden" name="keyword" value="${pageVO.keyword}">
	</form>
	
	<script type="text/javascript">
	//formOjb에 form id=f1의 객체 레퍼런스를 저장
	var formObj = $("#f1");	//id찾을때는 #, class찾을때는 . (dot) 
	
//		현재 페이지의 pagination class 내의 a 태그가 있는 놈을 찾음.
		$(".pagination a").click(function(e) {
			e.preventDefault(); 						//막는 기능
			var page = $(this).attr("href"); 		//클릭한 놈(this)의 속성중 href를 bno에 담아놓음.
			alert("page = " + page);
			
			formObj.find("[name='page']").val(page);	// form태그의 (id="f1") name이 page인 놈을 찾고, value에 저장 .val(page)
			
			formObj.submit();		//form태그의 action url로 get방식으로 server에 전송
		});
	
		//serch버튼을 눌렀을때 수행logic
		$("#btnSearch").click(function(e) {
			var type = $("#typeSelect").find(":selected").val();	//선택된 놈의 value를 저장
			var keyword = $("#xxx").val();
			
			alert("type = " + type + ", keyword = " + keyword);
			
			formObj.find("[name='type']").val(type);
			formObj.find("[name='keyword']").val(keyword);
			
			formObj.submit();
		});
	</script>
</body>
</html>