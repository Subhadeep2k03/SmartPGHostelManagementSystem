<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Room</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/RoomServlet">⬅ Back to Rooms</a>
</div>

<h2>Add Room</h2>



<form action="${pageContext.request.contextPath}/RoomServlet" method="post">


<input type="hidden" name="action" value="add">



<label>Room Number</label>

<input type="text" name="roomNumber" required>



<label>Floor</label>

<input type="number" name="floor" required>



<label>Room Type</label>

<input type="text" name="roomType" required>



<label>Capacity</label>

<input type="number" name="capacity" required>



<label>Rent</label>

<input type="number" name="rent" required>



<label>Status</label>

<input type="text" name="status" required>



<input type="submit" value="Add Room">


</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
