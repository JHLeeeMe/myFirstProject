<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8>
	<title>Emp modify.jsp</title>

	<c:if test="${false}">
		<link rel="stylesheet" href="../../css/bootstrap.css">
		<link rel="stylesheet" href="../../css/animate.css">
	</c:if>
</head>
<body>
	<div class="panel-heading">Emp Modify Page</div>
		<div class="panel-body">
	
		    <form id='f1'>
		
				<div class="form-group">
					<label>직원번호</label> 
					<input class="form-control" name="empno" value="${vo.empno}" />
				</div>
			
				<div class="form-group">
					<label>직원이름</label> 
					<input class="form-control" name="ename" value="${vo.ename}" />
				</div>
				
				<div class="form-group">
					<label>성별</label> 
					<select name="gender">
						<option>${vo.gender.name()}</option>
						<option value='M' ${pageVO.type == 'M' ? 'selected' : ''}>M</option>
						<option value='M' ${pageVO.type == 'F' ? 'selected' : ''}>F</option>
					</select>
					
					<label>직급</label>
					<select name="job">
						<option ${vo.job == '사원' ? 'selected' : ''}>부장</option>
						<option ${vo.job == '차장' ? 'selected' : ''}>차장</option>
						<option ${vo.job == '과장' ? 'selected' : ''}>과장</option>
						<option ${vo.job == '대리' ? 'selected' : ''}>대리</option>
						<option ${vo.job == '사원' ? 'selected' : ''}>사원</option>
					</select>
					
					<label>부서번호</label>
					<select name="deptno">
						<option ${vo.dept.deptno == '10' ? 'selected' : ''}>10</option>
						<option ${vo.dept.deptno == '20' ? 'selected' : ''}>20</option>
						<option ${vo.dept.deptno == '30' ? 'selected' : ''}>30</option>
					</select>
				</div>
				
				<div class="form-group">
					<label>입사일</label>
					<fmt:formatDate var="hiredate" value="${vo.hiredate}" pattern="MM/dd/yyyy"/> 
					<input class="form-control" name="hiredate" value="${hiredate}" />
					<p class="help-block">MM/dd/yyyy</p>
				</div>
				
				<div class="form-group">
					<label>급여</label> 
					<input class="form-control" name="sal" value="${vo.sal}" />
				</div>
				
				<div class="form-group">
					<label>보너스</label>
					<c:if test="${vo.comm != null}"> 
						<input class="form-control" name="comm" value="${vo.comm}" />
					</c:if>
					<c:if test="${vo.comm == null}">
						<input class="form-control" name="comm" value="${vo.comm}" readonly="readonly"/>
					</c:if>
				</div>
				
				<div class="form-group">
					<label>사수번호</label> 
					<input class="form-control" name="mgr" value="${vo.mgr.empno}"/>
				</div>
				
				<input type='hidden' name="page" value="${pageVO.page}">
				<input type='hidden' name="size" value="${pageVO.size}">
				<input type='hidden' name="type" value="${pageVO.type}">
				<input type='hidden' name="keyword" value="${pageVO.keyword}">
				
			</form>
	
			<div class="pull-right">
			
				<a href="#" class="btn btn-warning modbtn">Modify</a>
			
				<a href="#" class="btn btn-danger delbtn">Delete</a>
	
				<a href="/emp/list?page=${pageVO.page}&
										size=${pageVO.size}&
										type=${pageVO.type}&
										keyword=${pageVO.keyword}" class="btn btn-primary">
					Cancel & Go List
				</a>
			 
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