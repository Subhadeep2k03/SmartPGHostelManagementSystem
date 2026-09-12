<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Admin Login | Smart PG Hostel</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

body{
    min-height:100vh;
    display:flex;
    align-items:center;
    justify-content:center;
    padding:20px;
}

.login-box{
    width:100%;
    max-width:380px;
    margin:0;
    text-align:center;
}

.login-box .brand{
    font-size:34px;
    margin-bottom:6px;
}

.login-box h2{
    margin:0 0 4px;
}

.login-box p.subtitle{
    margin:0 0 20px;
    font-size:14px;
}

.login-box form{
    box-shadow:none;
    border:none;
    padding:0;
    margin:0;
    max-width:none;
}

.login-box label{
    text-align:left;
}

</style>

</head>

<body>

<div class="login-box card-box">

<div class="brand">🏢</div>
<h2>Smart PG Hostel</h2>
<p class="subtitle text-muted">Admin sign-in</p>

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

<form action="LoginServlet" method="post">

<label>Username</label>
<input type="text" name="username" placeholder="Enter your username" required>

<label>Password</label>
<input type="password" name="password" placeholder="Enter your password" required>

<button type="submit">

Login

</button>

</form>

</div>

</body>

</html>
