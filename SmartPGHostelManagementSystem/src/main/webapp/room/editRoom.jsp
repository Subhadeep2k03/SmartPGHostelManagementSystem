<%@ page import="com.smartpg.model.Room"%>

<%
Room room = (Room) request.getAttribute("room");
%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Room</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/RoomServlet">⬅ Back to Rooms</a>
</div>

<h2>Edit Room</h2>



<form action="${pageContext.request.contextPath}/RoomServlet" method="post">


<input type="hidden" name="action" value="update">


<input type="hidden" name="roomId"
value="<%=room.getRoomId()%>">



<label>Room Number</label>

<input type="text"
name="roomNumber"
value="<%=room.getRoomNumber()%>">




<label>Floor</label>

<input type="number"
name="floor"
value="<%=room.getFloor()%>">




<label>Room Type</label>

<input type="text"
name="roomType"
value="<%=room.getRoomType()%>">




<label>Capacity</label>

<input type="number"
name="capacity"
value="<%=room.getCapacity()%>">




<label>Rent</label>

<input type="number"
name="rent"
value="<%=room.getRent()%>">




<label>Status</label>

<input type="text"
name="status"
value="<%=room.getStatus()%>">




<input type="submit" value="Update Room">


</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
