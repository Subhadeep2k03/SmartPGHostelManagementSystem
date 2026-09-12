<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Student Registration | Smart PG Hostel</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

body{
    padding:30px 20px;
}

.register-box{
    max-width:560px;
    margin:0 auto;
    text-align:center;
}

.register-box .brand{
    font-size:34px;
    margin-bottom:6px;
}

.register-box form{
    text-align:left;
}

.switch-link{
    display:block;
    margin-top:14px;
    font-size:14px;
}

</style>

</head>

<body>

<div class="register-box">

<div class="brand">🏠</div>
<h2>Create Your Student Account</h2>
<p class="page-subtitle">Smart PG Hostel Management System</p>

<%

String error = (String) request.getAttribute("error");

if(error != null){

%>

<p class="alert alert-error">

<%=error%>

</p>

<%

}

%>

<form action="StudentRegisterServlet" method="post">

<label>First Name</label>
<input type="text" name="firstName" required>

<label>Last Name</label>
<input type="text" name="lastName">

<label>Gender</label>
<select name="gender" required>
<option value="">Select</option>
<option value="Male">Male</option>
<option value="Female">Female</option>
<option value="Other">Other</option>
</select>

<label>Date of Birth</label>
<input type="date" name="dob" required>

<label>Phone</label>
<input type="text" name="phone" required>

<label>Email</label>
<input type="email" name="email" required>

<label>Address</label>
<textarea name="address" rows="3"></textarea>

<label>Guardian Name</label>
<input type="text" name="guardianName" required>

<label>Guardian Phone</label>
<input type="text" name="guardianPhone" required>

<label>Password</label>
<input type="password" name="password" minlength="8" required>

<label>Confirm Password</label>
<input type="password" name="confirmPassword" minlength="8" required>

<button type="submit">

Create Account

</button>

</form>

<a class="switch-link" href="studentLogin.jsp">Already have an account? Log in</a>

</div>

</body>

</html>
