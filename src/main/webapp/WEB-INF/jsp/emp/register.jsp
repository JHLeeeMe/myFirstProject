<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8>
	<title>Emp register.jsp</title>

	<c:if test="${false}">
		<link rel="stylesheet" href="../../css/bootstrap.css">
		<link rel="stylesheet" href="../../css/animate.css">
	</c:if>
</head>
<body>
	<div class="panel-heading">Emp Register Page</div>
		<div class="panel-body">
	
			<form action="/emp/register" method="post">
				<div class="form-group">
					<label>Empno</label>
					<input class="form-control" name="empno" value="${vo.empno}"/>
				</div>

				<div class="form-group">
					<label>Ename</label>
					<input class="form-control" name="ename" value="${vo.ename}" />
				</div>
	
				<div class="form-group">
					<label>Gender</label>
					<select name="gender">
						<option>--</option>
						<option>M</option>
						<option>F</option>
					</select>
				
					<label>Job</label>
					<select name="job">
						<option>--</option>
						<option>부장</option>
						<option>차장</option>
						<option>과장</option>
						<option>대리</option>
						<option>사원</option>
					</select>
				
					<label>Deptno</label>
					<select name="deptno">
						<option>--</option>
						<option>10</option>
						<option>20</option>
						<option>30</option>
					</select>
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label>Hiredate</label> -->
<%-- 					<input class="form-control" name="hiredate" value="${#dates.format(vo.hiredate, 'MM/dd/yyyy')}"/> --%>
<!-- 					<p class="help-block">mm/dd/yyyy</p> -->
<!-- 				</div> -->
				
				<div class="form-group">
					<label>Sal</label>
					<input class="form-control" name="sal" value="${vo.sal}"/>
				</div>
				
				<div class="form-group">
					<label>Comm</label>
					<input class="form-control" name="comm" value="${vo.comm}" />
				</div>
				
				<div class="form-group">
					<label>Mgr</label>
					<input class="form-control" name="mgr" value="${vo.mgr}" />
				</div>
	
				<button type="submit" class="btn btn-default">Submit Button</button>
				<button type="reset" class="btn btn-primary">Reset Button</button>
							
			</form>
	
		</div>
		
</body>
</html>