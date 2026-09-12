<%@ page import="java.util.List"%>
<%@ page import="com.smartpg.model.Room"%>

<%
List<Room> rooms = (List<Room>) request.getAttribute("rooms");
%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Room List</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>


<jsp:include page="../common/header.jsp"/>


<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="<%=request.getContextPath()%>/room/addRoom.jsp">
➕ Add Room
</a>

</div>


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


<h2>Room List</h2>


<table>


<tr>

<th>ID</th>
<th>Room Number</th>
<th>Floor</th>
<th>Room Type</th>
<th>Capacity</th>
<th>Rent</th>
<th>Status</th>
<th>Edit</th>
<th>Delete</th>

</tr>




<%

if(rooms != null && !rooms.isEmpty()){


for(Room room : rooms){

%>



<tr>


<td data-label="ID">
<%=room.getRoomId()%>
</td>



<td data-label="Room Number">
<%=room.getRoomNumber()%>
</td>



<td data-label="Floor">
<%=room.getFloor()%>
</td>



<td data-label="Room Type">
<%=room.getRoomType()%>
</td>



<td data-label="Capacity">
<%=room.getCapacity()%>
</td>



<td data-label="Rent">
<%=room.getRent()%>
</td>



<td data-label="Status">
<%=room.getStatus()%>
</td>




<td data-label="Edit">

<a href="<%=request.getContextPath()%>/EditRoomServlet?id=<%=room.getRoomId()%>">

Edit

</a>

</td>




<td data-label="Delete">

<a href="<%=request.getContextPath()%>/DeleteRoomServlet?id=<%=room.getRoomId()%>"
onclick="return confirm('Delete this room?')">

Delete

</a>

</td>



</tr>




<%

}

}else{

%>


<tr>

<td colspan="9" class="empty-state">

No Room Found

</td>

</tr>


<%

}

%>



</table>



<jsp:include page="../common/footer.jsp"/>



</body>


</html>
