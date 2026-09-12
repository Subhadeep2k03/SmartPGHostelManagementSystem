<%@page import="java.util.List"%>
<%@page import="com.smartpg.dao.PaymentDAO"%>
<%@page import="com.smartpg.model.Payment"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>View Payments</title>

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

<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="addPayment.jsp">
➕ Add New Payment
</a>

</div>

<h2>Payment List</h2>

<table>

<tr>
<th>Payment ID</th>
<th>Student ID</th>
<th>Amount</th>
<th>Payment Date</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
PaymentDAO dao = new PaymentDAO();

List<Payment> list = dao.getAllPayments();

if(list != null && !list.isEmpty()){

for(Payment p : list){
%>

<tr>

<td data-label="Payment ID"><%=p.getPaymentId()%></td>
<td data-label="Student ID"><%=p.getStudentId()%></td>
<td data-label="Amount"><%=p.getAmount()%></td>
<td data-label="Payment Date"><%=p.getPaymentDate()%></td>
<td data-label="Status"><%=p.getStatus()%></td>

<td data-label="Action">

<a href="editPayment.jsp?id=<%=p.getPaymentId()%>">
Edit
</a>

&nbsp;

<a href="<%=request.getContextPath()%>/PaymentServlet?action=delete&id=<%=p.getPaymentId()%>"
onclick="return confirm('Are you sure?')">
Delete
</a>

</td>

</tr>

<%
}
} else {
%>

<tr>
<td colspan="6" class="empty-state">No Payments Found</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
