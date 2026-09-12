<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Room Allocation</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/RoomAllocationServlet">⬅ Back to Allocations</a>
</div>

<h2>Add Room Allocation</h2>

<form action="../RoomAllocationServlet" method="post">

<input type="hidden" name="action" value="add">

<label>Student ID</label>
<input type="number" name="studentId" required>

<label>Room ID</label>
<input type="number" name="roomId" required>

<label>Allocation Date</label>
<input type="date" name="allocationDate" required>

<label>End Date</label>
<input type="date" name="endDate">

<label>Status</label>
<input type="text" name="status" value="Active">

<input type="submit" value="Allocate Room">

</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
