<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management CRUD</title>

</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">



<c:set var="employees" value="<%=request.getAttribute(\"employees\") %>"/>

	
	<h1 class="mx-auto text-center py-5 w-50">Welcome to SPRK TECH Employee Management System</h1>
	
	
	<c:set var="successMsg" value="<%=session.getAttribute(\"successMsg\") %>"/>
	<c:if test="${not empty successMsg}">
		<div class="alert alert-success w-50 text-center mx-auto mb-5" role="alert">
  			${successMsg}
		</div>
		<c:remove var="successMsg"/>
	</c:if>	
	
	<c:set var="errMsg" value="<%=session.getAttribute(\"errMsg\") %>"/>
	<c:if test="${not empty errMsg}">
		<div class="alert alert-danger w-50 text-center mx-auto mb-5" role="alert">
  			${errMsg}
		</div>
		<c:remove var="errMsg"/>
	</c:if>	
	
	
	<table class="table table-bordered w-100 mx-auto">
	
		<tr class="table-dark">
			<th>Emp Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="tempEmp" items="${employees}">
			<tr>
				<td>${tempEmp.empId }</td>
				<td>${tempEmp.firstName }</td>
				<td>${tempEmp.lastName }</td>
				<td>${tempEmp.email }</td>
				<td>${tempEmp.gender }</td>
				<td>${tempEmp.address}</td>
				<td>
					<!-- Links -->
						<c:url var="updateLink" value="/employee/update">
						<c:param name="empId" value="${tempEmp.empId}"></c:param>
					</c:url>
					<a class="btn btn-sm btn-outline-dark" href="${updateLink}">Update</a> | 
					<c:url var="deleteLink" value="/employee/delete">
						<c:param name="empId" value="${tempEmp.empId}"></c:param>
					</c:url>
					<a class="btn btn-sm btn-outline-danger" href="${deleteLink}">Delete</a>
				</td>
			</tr>
		
		</c:forEach>
	</table>
</div>

</body>
</html>