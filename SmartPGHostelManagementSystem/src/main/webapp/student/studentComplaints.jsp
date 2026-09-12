<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.Complaint"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Complaints</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

textarea{
    height:90px;
    resize:vertical;
}

</style>

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<%
String message = (String) session.getAttribute("message");

if(message != null){
%>

<div class="alert alert-success">
<%=message%>
</div>

<%
session.removeAttribute("message");
}
%>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>Submit a Complaint</h2>

<form action="<%=request.getContextPath()%>/student/StudentComplaintServlet" method="post">

<label>Room ID</label>
<input type="number" name="roomId" required>

<label>Description</label>
<textarea name="description" required></textarea>

<button type="submit">Submit Complaint</button>

</form>

<h2>My Complaints</h2>

<%
List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");

if (complaints != null && !complaints.isEmpty()) {
%>

<table>

<tr>
<th>Room ID</th>
<th>Date</th>
<th>Description</th>
<th>Status</th>
</tr>

<%
    for (Complaint c : complaints) {

        String badgeClass = "Resolved".equalsIgnoreCase(c.getStatus()) ? "badge-success" : "badge-warning";
%>

<tr>
<td data-label="Room ID"><%=c.getRoomId()%></td>
<td data-label="Date"><%=c.getComplaintDate()%></td>
<td data-label="Description"><%=c.getDescription()%></td>
<td data-label="Status"><span class="badge <%=badgeClass%>"><%=c.getStatus()%></span></td>
</tr>

<%
    }
%>

</table>

<% } else { %>

<div class="empty-state">You haven't submitted any complaints yet.</div>

<% } %>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
