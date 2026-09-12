<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Payment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="viewPayments.jsp">⬅ Back to Payments</a>
</div>

<h2>Add Payment</h2>


<form action="${pageContext.request.contextPath}/PaymentServlet" method="post">

<label>Student ID</label>
<input type="number" name="studentId" required>

<label>Amount</label>
<input type="number" name="amount" required>

<label>Payment Date</label>
<input type="date" name="paymentDate" required>

<label>Status</label>

<select name="status">
    <option value="Paid">Paid</option>
    <option value="Pending">Pending</option>
</select>

<input type="submit" value="Add Payment">

</form>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>
