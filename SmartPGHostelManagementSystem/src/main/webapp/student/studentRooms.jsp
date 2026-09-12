<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Available Rooms</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>Available Rooms</h2>
<p class="page-subtitle">Rooms currently open for allocation across the hostel.</p>

<table>

<tr>
<th>Room Number</th>
<th>Floor</th>
<th>Type</th>
<th>Capacity</th>
<th>Rent</th>
<th>Status</th>
</tr>

<%
List<Room> rooms = (List<Room>) request.getAttribute("rooms");

if (rooms != null && !rooms.isEmpty()) {

    for (Room r : rooms) {
%>

<tr>
<td data-label="Room Number"><%=r.getRoomNumber()%></td>
<td data-label="Floor"><%=r.getFloor()%></td>
<td data-label="Type"><%=r.getRoomType()%></td>
<td data-label="Capacity"><%=r.getCapacity()%></td>
<td data-label="Rent">₹<%=r.getRent()%></td>
<td data-label="Status"><span class="badge badge-success"><%=r.getStatus()%></span></td>
</tr>

<%
    }
} else {
%>

<tr>
<td colspan="6" class="empty-state">No rooms are currently available</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
