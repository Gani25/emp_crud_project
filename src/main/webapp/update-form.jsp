<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management CRUD</title>

</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
	
	<h1 class="mx-auto text-center my-5 w-50">Welcome to SPRK TECH Employee Management System</h1>
	
	<%
		Map<String,String> errorMessages = (Map<String,String>)request.getAttribute("errorMessages");
		if(errorMessages == null){
			errorMessages = new HashMap<>();
		}
	%>
	
	<c:set var="employee" value="<%=request.getAttribute(\"employee\") %>"/>
	
	<form class="row g-5 justify-content-center" method="post" action="<%=request.getContextPath() %>/employee/update">
	  
	  <%-- <input type="hidden" name="empId" value="${employee.empId}">
	   --%>
	  <div class="col-md-5">
	    <label for="inputFirstName" class="form-label">First Name</label>
	    <input type="text" class="form-control" id="inputFirstName" placeholder="John" name="first_name" value="${employee.firstName}">
	    <%
	    	if(errorMessages.containsKey("firstName")){
	    		out.print("<p class=\"text-danger\">"+errorMessages.get("firstName")+"</p>");
	    	}
	    %>
	  </div>
	  <div class="col-md-5">
	    <label for="inputLastName" class="form-label">Last Name</label>
	    <input type="text" class="form-control" id="inputLastName" placeholder="Doe" name="last_name" value="${employee.lastName}">
	  	<%
	    	if(errorMessages.containsKey("lastName")){
	    		out.print("<p class=\"text-danger\">"+errorMessages.get("lastName")+"</p>");
	    	}
	    %>
	  </div>
	  
	  <div class="col-md-5">
	    <label for="inputEmail" class="form-label">Email</label>
	    <input type="text" class="form-control" id="inputEmail" placeholder="john123@gmail.com" name="email" value="${employee.email}">
	  	<%
	    	if(errorMessages.containsKey("email")){
	    		out.print("<p class=\"text-danger\">"+errorMessages.get("email")+"</p>");
	    	}
	    %>
	  </div>
	   <div class="col-md-5">
	    <label for="inputGender" class="form-label">Gender</label>
	    <select id="inputGender" class="form-select" name="gender">
	      <option selected value="">Choose Gender...</option>
	      <option value="Male" <c:if test="${employee.gender eq 'Male'}">selected</c:if>>Male</option>
	      <option value="Female" <c:if test="${employee.gender eq 'Female'}">selected</c:if>>Female</option>
	      <option value="Others" <c:if test="${employee.gender eq 'Others'}">selected</c:if>>Others</option>
	    </select>
	  </div>
	  <div class="col-10">
	    <label for="inputAddress" class="form-label">Address</label>
	    <textarea class="form-control" id="inputAddress" name="address" placeholder="1234 Main St">${employee.address}</textarea>
	  	<%
	    	if(errorMessages.containsKey("address")){
	    		out.print("<p class=\"text-danger\">"+errorMessages.get("address")+"</p>");
	    	}
	    %>
	  </div>
	  
	  <div class="col-10">
	    <button type="submit" class="btn btn-primary">Sign in</button>
	  </div>
	</form>
</div>

</body>
</html>