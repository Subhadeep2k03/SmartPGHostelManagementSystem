<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smartpg.model.Complaint"%>

<%
Complaint complaint = (Complaint) request.getAttribute("complaint");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Complaint</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

.field-note{
    background:#fbfbfe;
    border:1.5px solid var(--border);
    border-radius:var(--radius-sm);
    padding:11px 13px;
    margin:6px 0 4px;
    color:var(--text-muted);
    font-size:14.5px;
}

</style>

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/ComplaintServlet">⬅ Back to Complaints</a>
</div>

<%
if (complaint == null) {
%>

<p class="empty-state">Complaint not found!</p>

<%
} else {
%>

<h2>Edit Complaint #<%=complaint.getComplaintId()%></h2>

<form action="<%=request.getContextPath()%>/ComplaintServlet" method="post">

<input type="hidden" name="action" value="update">

<input type="hidden" name="complaintId" value="<%=complaint.getComplaintId()%>">

<label>Student ID</label>
<div class="field-note"><%=complaint.getStudentId()%></div>

<label>Room ID</label>
<div class="field-note"><%=complaint.getRoomId()%></div>

<label>Complaint Date</label>
<div class="field-note"><%=complaint.getComplaintDate()%></div>

<label>Description</label>
<div class="field-note"><%=complaint.getDescription()%></div>

<label>Status</label>

<select name="status">

    <option value="Pending" <%= "Pending".equals(complaint.getStatus()) ? "selected" : "" %>>Pending</option>
    <option value="Resolved" <%= "Resolved".equals(complaint.getStatus()) ? "selected" : "" %>>Resolved</option>

</select>

<input type="submit" value="Update Complaint">

</form>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>
