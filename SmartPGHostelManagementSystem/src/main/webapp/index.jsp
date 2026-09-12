<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Smart PG Hostel Management System</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

body{
    min-height:100vh;
    display:flex;
    align-items:center;
    justify-content:center;
    padding:20px;
}

.container{
    width:100%;
    max-width:380px;
    margin:0;
    text-align:center;
}

.container .brand{
    font-size:34px;
    margin-bottom:6px;
}

.container h2{
    margin:0 0 20px;
    font-size:22px;
}

.container form{
    box-shadow:none;
    border:none;
    padding:0;
    margin:0;
    max-width:none;
    text-align:left;
}

</style>

</head>


<body>


<div class="container card-box">

<div class="brand">🏢</div>
<h2>Smart PG Hostel Management System</h2>


<form action="LoginServlet" method="post">


<label>Username</label>
<input type="text" name="username" placeholder="Username" required>


<label>Password</label>
<input type="password" name="password" placeholder="Password" required>


<button type="submit">
Login
</button>


</form>

<p style="margin-top:16px; font-size:14px; text-align:center;">
Are you a student?
<a href="student/studentLogin.jsp">Go to Student Login</a>
</p>

</div>


</body>

</html>
