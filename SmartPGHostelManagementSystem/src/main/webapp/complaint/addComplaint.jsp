<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Complaint</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

textarea{
    height:100px;
    resize:vertical;
}

</style>

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/ComplaintServlet">⬅ Back to Complaints</a>
</div>

<h2>Add Complaint</h2>

<form action="${pageContext.request.contextPath}/ComplaintServlet" method="post">

<input type="hidden" name="action" value="add">

<label>Student ID</label>
<input type="number" name="studentId" required>

<label>Room ID</label>
<input type="number" name="roomId" required>

<label>Complaint Date</label>
<input type="date" name="complaintDate" required>

<label>Description</label>
<textarea name="description" required></textarea>

<label>Status</label>
<input type="text" name="status" value="Pending" readonly>

<button type="submit">
Submit Complaint
</button>

</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
