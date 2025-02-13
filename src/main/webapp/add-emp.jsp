<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management CRUD</title>

</head>
<body>

<jsp:include page="navbar.html"></jsp:include>

<div class="container">
	
	<h1 class="mx-auto text-center py-5 w-50">Welcome to SPRK TECH Employee Management System</h1>
	
	<form class="row g-5 justify-content-center" method="post" action="employee">
	  <div class="col-md-5">
	    <label for="inputFirstName" class="form-label">First Name</label>
	    <input type="text" class="form-control" id="inputFirstName" placeholder="John" name="first_name">
	  </div>
	  <div class="col-md-5">
	    <label for="inputLastName" class="form-label">Last Name</label>
	    <input type="text" class="form-control" id="inputLastName" placeholder="Doe" name="last_name">
	  </div>
	  
	  <div class="col-md-5">
	    <label for="inputEmail" class="form-label">Email</label>
	    <input type="text" class="form-control" id="inputEmail" placeholder="john123@gmail.com" name="email">
	  </div>
	   <div class="col-md-5">
	    <label for="inputGender" class="form-label">Gender</label>
	    <select id="inputGender" class="form-select" name="gender">
	      <option selected value="Choose Gender">Choose Gender...</option>
	      <option value="Male">Male</option>
	      <option value="Female">Female</option>
	      <option value="Others">Others</option>
	    </select>
	  </div>
	  <div class="col-10">
	    <label for="inputAddress" class="form-label">Address</label>
	    <textarea class="form-control" id="inputAddress" name="address" placeholder="1234 Main St"></textarea>
	  </div>
	  
	  <div class="col-10">
	    <button type="submit" class="btn btn-primary">Sign in</button>
	  </div>
	</form>
</div>

</body>
</html>