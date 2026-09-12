<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.smartpg.model.Visitor"%>

<%
Visitor visitor = (Visitor) request.getAttribute("visitor");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Visitor</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/VisitorServlet?action=list">⬅ Back to Visitors</a>
</div>

<%
if (visitor == null) {
%>

<p class="empty-state">Visitor not found!</p>

<%
} else {
%>

<h2>Edit Visitor</h2>

<form action="<%=request.getContextPath()%>/VisitorServlet" method="post">

<input type="hidden" name="visitorId" value="<%=visitor.getVisitorId()%>">

<label>Student ID</label>
<input type="number" name="studentId" value="<%=visitor.getStudentId()%>" required>

<label>Visitor Name</label>
<input type="text" name="visitorName" value="<%=visitor.getVisitorName()%>" required>

<label>Phone</label>
<input type="text" name="phone" value="<%=visitor.getPhone()%>" required>

<label>Relation</label>
<input type="text" name="relation" value="<%=visitor.getRelation()%>" required>

<label>Visit Date</label>
<input type="date" name="visitDate" value="<%=visitor.getVisitDate()%>" required>

<label>In Time</label>
<input type="time" name="inTime" value="<%=visitor.getInTime()%>" required>

<label>Out Time</label>
<input type="time" name="outTime" value="<%=visitor.getOutTime()%>">

<button type="submit">
Update Visitor
</button>

</form>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
