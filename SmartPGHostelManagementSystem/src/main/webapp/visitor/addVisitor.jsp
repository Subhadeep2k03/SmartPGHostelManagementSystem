<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Visitor</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/VisitorServlet?action=list">⬅ Back to Visitors</a>
</div>

<h2>Add Visitor</h2>

<form action="<%=request.getContextPath()%>/VisitorServlet" method="post">

<label>Student ID</label>
<input type="number" name="studentId" required>

<label>Visitor Name</label>
<input type="text" name="visitorName" required>

<label>Phone</label>
<input type="text" name="phone" required>

<label>Relation</label>
<input type="text" name="relation" required>

<label>Visit Date</label>
<input type="date" name="visitDate" required>

<label>In Time</label>
<input type="time" name="inTime" required>

<label>Out Time</label>
<input type="time" name="outTime">

<button type="submit">
Add Visitor
</button>

</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
