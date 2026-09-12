<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.RoomAllocation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Room Allocation</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>My Room Allocation</h2>

<%
List<RoomAllocation> allocations = (List<RoomAllocation>) request.getAttribute("allocations");

if (allocations != null && !allocations.isEmpty()) {
%>

<table>

<tr>
<th>Room ID</th>
<th>Allocation Date</th>
<th>End Date</th>
<th>Status</th>
</tr>

<%
    for (RoomAllocation a : allocations) {

        String badgeClass = "Active".equalsIgnoreCase(a.getStatus()) ? "badge-success" : "badge-warning";
%>

<tr>
<td data-label="Room ID"><%=a.getRoomId()%></td>
<td data-label="Allocation Date"><%=a.getAllocationDate()%></td>
<td data-label="End Date"><%=a.getEndDate() != null ? a.getEndDate() : "-"%></td>
<td data-label="Status"><span class="badge <%=badgeClass%>"><%=a.getStatus()%></span></td>
</tr>

<%
    }
%>

</table>

<% } else { %>

<div class="empty-state">You don't have a room allocation yet.</div>

<% } %>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
