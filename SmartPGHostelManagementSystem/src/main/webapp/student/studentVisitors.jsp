<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.Visitor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Visitors</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>My Visitors</h2>

<%
List<Visitor> visitors = (List<Visitor>) request.getAttribute("visitors");

if (visitors != null && !visitors.isEmpty()) {
%>

<table>

<tr>
<th>Visitor Name</th>
<th>Relation</th>
<th>Phone</th>
<th>Visit Date</th>
<th>In Time</th>
<th>Out Time</th>
</tr>

<%
    for (Visitor v : visitors) {
%>

<tr>
<td data-label="Visitor Name"><%=v.getVisitorName()%></td>
<td data-label="Relation"><%=v.getRelation()%></td>
<td data-label="Phone"><%=v.getPhone()%></td>
<td data-label="Visit Date"><%=v.getVisitDate()%></td>
<td data-label="In Time"><%=v.getInTime()%></td>
<td data-label="Out Time"><%=v.getOutTime() != null ? v.getOutTime() : "-"%></td>
</tr>

<%
    }
%>

</table>

<% } else { %>

<div class="empty-state">No visitor records found.</div>

<% } %>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
