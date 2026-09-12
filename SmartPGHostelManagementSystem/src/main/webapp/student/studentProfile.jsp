<%@page import="com.smartpg.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Profile</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>My Profile</h2>

<%
Student student = (Student) request.getAttribute("student");
%>

<% if (student != null) { %>

<table>

<tr><th>Field</th><th>Value</th></tr>

<tr><td data-label="Field">First Name</td><td data-label="Value"><%=student.getFirstName()%></td></tr>
<tr><td data-label="Field">Last Name</td><td data-label="Value"><%=student.getLastName() != null ? student.getLastName() : ""%></td></tr>
<tr><td data-label="Field">Gender</td><td data-label="Value"><%=student.getGender()%></td></tr>
<tr><td data-label="Field">Date of Birth</td><td data-label="Value"><%=student.getDob()%></td></tr>
<tr><td data-label="Field">Phone</td><td data-label="Value"><%=student.getPhone()%></td></tr>
<tr><td data-label="Field">Email</td><td data-label="Value"><%=student.getEmail()%></td></tr>
<tr><td data-label="Field">Address</td><td data-label="Value"><%=student.getAddress() != null ? student.getAddress() : ""%></td></tr>
<tr><td data-label="Field">Guardian Name</td><td data-label="Value"><%=student.getGuardianName()%></td></tr>
<tr><td data-label="Field">Guardian Phone</td><td data-label="Value"><%=student.getGuardianPhone()%></td></tr>
<tr><td data-label="Field">Join Date</td><td data-label="Value"><%=student.getJoinDate()%></td></tr>
<tr><td data-label="Field">Status</td><td data-label="Value">
<span class="badge <%= "Active".equalsIgnoreCase(student.getStatus()) ? "badge-success" : "badge-warning" %>">
<%=student.getStatus()%>
</span>
</td></tr>

</table>

<% } else { %>

<div class="empty-state">Profile could not be loaded.</div>

<% } %>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
