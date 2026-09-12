<%@ page import="java.util.List" %>
<%@ page import="com.smartpg.model.RoomAllocation" %>

<%
List<RoomAllocation> allocations =
        (List<RoomAllocation>) request.getAttribute("allocations");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>View Room Allocations</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

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

<h2>Room Allocation List</h2>

<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="<%=request.getContextPath()%>/roomallocation/addRoomAllocation.jsp">
➕ Add New Allocation
</a>

</div>

<table>

<tr>
<th>Allocation ID</th>
<th>Student ID</th>
<th>Room ID</th>
<th>Allocation Date</th>
<th>End Date</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
if(allocations != null && !allocations.isEmpty()){

for(RoomAllocation allocation : allocations){
%>

<tr>

<td data-label="Allocation ID"><%=allocation.getAllocationId()%></td>
<td data-label="Student ID"><%=allocation.getStudentId()%></td>
<td data-label="Room ID"><%=allocation.getRoomId()%></td>
<td data-label="Allocation Date"><%=allocation.getAllocationDate()%></td>
<td data-label="End Date"><%=allocation.getEndDate()%></td>
<td data-label="Status"><%=allocation.getStatus()%></td>

<td data-label="Action">

<a href="<%=request.getContextPath()%>/roomallocation/editRoomAllocation.jsp?id=<%=allocation.getAllocationId()%>">
Edit
</a>

&nbsp;

<a href="<%=request.getContextPath()%>/RoomAllocationServlet?action=delete&id=<%=allocation.getAllocationId()%>"
onclick="return confirm('Delete this allocation?');">
Delete
</a>

</td>

</tr>

<%
}
} else {
%>

<tr>
<td colspan="7" class="empty-state">No Room Allocations Found</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
