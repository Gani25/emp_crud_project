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
	
	
	
	<table class="table table-bordered w-75 mx-auto">
	
		<tr class="table-dark">
			<th>Emp Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>Address</th>
		</tr>
		
		<c:forEach var="tempEmp" items="${employees}">
			<tr>
				<td>${tempEmp.empId }</td>
				<td>${tempEmp.firstName }</td>
				<td>${tempEmp.lastName }</td>
				<td>${tempEmp.email }</td>
				<td>${tempEmp.gender }</td>
				<td>${tempEmp.address}</td>
			</tr>
		
		</c:forEach>
	</table>
</div>

</body>
</html>