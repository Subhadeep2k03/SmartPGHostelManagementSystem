<%@ page import="com.smartpg.dao.RoomAllocationDAO" %>
<%@ page import="com.smartpg.model.RoomAllocation" %>

<%
int id = Integer.parseInt(request.getParameter("id"));

RoomAllocationDAO dao = new RoomAllocationDAO();

RoomAllocation allocation = dao.getAllocationById(id);
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Room Allocation</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/RoomAllocationServlet">⬅ Back to Allocations</a>
</div>

<%
if (allocation == null) {
%>

<p class="empty-state">Allocation not found!</p>

<%
} else {
%>

<h2>Edit Room Allocation</h2>

<form action="../RoomAllocationServlet" method="post">

<input type="hidden" name="action" value="update">

<input type="hidden" name="allocationId" value="<%=allocation.getAllocationId()%>">

<label>Student ID</label>
<input type="number" name="studentId" value="<%=allocation.getStudentId()%>" readonly>

<label>Room ID</label>
<input type="number" name="roomId" value="<%=allocation.getRoomId()%>" readonly>

<label>Allocation Date</label>
<input type="date" name="allocationDate" value="<%=allocation.getAllocationDate()%>" readonly>

<label>End Date</label>
<input type="date" name="endDate" value="<%=allocation.getEndDate()%>">

<label>Status</label>
<input type="text" name="status" value="<%=allocation.getStatus()%>">

<input type="submit" value="Update Allocation">

</form>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
