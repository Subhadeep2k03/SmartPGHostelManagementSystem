<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.Payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>My Payments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>My Payments</h2>

<%
List<Payment> payments = (List<Payment>) request.getAttribute("payments");

if (payments != null && !payments.isEmpty()) {
%>

<table>

<tr>
<th>Payment ID</th>
<th>Amount</th>
<th>Payment Date</th>
<th>Status</th>
</tr>

<%
    for (Payment p : payments) {

        String badgeClass = "Paid".equalsIgnoreCase(p.getStatus()) ? "badge-success" : "badge-warning";
%>

<tr>
<td data-label="Payment ID"><%=p.getPaymentId()%></td>
<td data-label="Amount">₹<%=p.getAmount()%></td>
<td data-label="Payment Date"><%=p.getPaymentDate()%></td>
<td data-label="Status"><span class="badge <%=badgeClass%>"><%=p.getStatus()%></span></td>
</tr>

<%
    }
%>

</table>

<% } else { %>

<div class="empty-state">No payment records found.</div>

<% } %>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
