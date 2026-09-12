<%@page import="com.smartpg.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Student Dashboard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

.container{
    width:100%;
    margin:10px auto 0;
    text-align:center;
}

h1{
    margin:6px 0 4px;
    font-size:26px;
}

.welcome-sub{
    color:var(--text-muted);
    margin-bottom:10px;
    font-size:14.5px;
}

.card-container{
    display:flex;
    justify-content:center;
    flex-wrap:wrap;
    gap:4px;
}

.card{
    width:210px;
    padding:24px 20px;
    margin:12px;
    background:var(--surface);
    border-radius:var(--radius);
    box-shadow:var(--shadow);
    border:1px solid var(--border);
    transition:transform .25s ease, box-shadow .25s ease;
}

.card:hover{
    transform:translateY(-8px);
    box-shadow:var(--shadow-lg);
}

.card a{
    text-decoration:none;
    color:var(--text);
}

.icon{
    font-size:36px;
    margin-bottom:8px;
}

.management-card:hover{
    background:linear-gradient(120deg, var(--primary), var(--primary-dark));
    border-color:var(--primary);
}

.management-card:hover a{
    color:white;
}

h3{
    margin:10px 0 0;
    font-size:15.5px;
}

</style>

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="container">

<%
Student student = (Student) request.getAttribute("student");
String firstName = (student != null) ? student.getFirstName() : (String) session.getAttribute("studentName");
%>

<h1>Welcome, <%=firstName%> 👋</h1>
<p class="welcome-sub">Here's your Smart PG Hostel account at a glance.</p>

<div class="card-container">

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentProfileServlet">
<div class="icon">👤</div>
<h3>My Profile</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentAllocationServlet">
<div class="icon">🔑</div>
<h3>My Room Allocation</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentRoomServlet">
<div class="icon">🏠</div>
<h3>Available Rooms</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentPaymentServlet">
<div class="icon">💳</div>
<h3>My Payments</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentVisitorServlet">
<div class="icon">🧑‍🤝‍🧑</div>
<h3>My Visitors</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentComplaintServlet">
<div class="icon">📋</div>
<h3>My Complaints</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentNoticeServlet">
<div class="icon">📢</div>
<h3>Notices</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/student/StudentLogoutServlet">
<div class="icon">🚪</div>
<h3>Logout</h3>
</a>
</div>

</div>

</div>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
