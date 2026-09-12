<%@ page import="java.sql.*" %>
<%@ page import="com.smartpg.util.DBConnection" %>

<%
int id = Integer.parseInt(request.getParameter("id"));

boolean found = false;
int paymentId = 0;
int studentId = 0;
double amount = 0;
Date paymentDate = null;
String status = "";

try (Connection con = DBConnection.getConnection();
     PreparedStatement ps = con.prepareStatement("SELECT * FROM payment WHERE payment_id=?")) {

    ps.setInt(1, id);

    try (ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            found = true;
            paymentId = rs.getInt("payment_id");
            studentId = rs.getInt("student_id");
            amount = rs.getDouble("amount");
            paymentDate = rs.getDate("payment_date");
            status = rs.getString("payment_status");
        }
    }
}

if (found) {
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Payment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="viewPayments.jsp">⬅ Back to Payments</a>
</div>

<h2>Edit Payment</h2>

<form action="../PaymentServlet" method="post">

<input type="hidden" name="action" value="update">

<input type="hidden" name="paymentId" value="<%=paymentId%>">

<label>Student ID</label>
<input type="number" name="studentId" value="<%=studentId%>" readonly>

<label>Amount</label>
<input type="number" name="amount" value="<%=amount%>" required>

<label>Payment Date</label>
<input type="date" name="paymentDate" value="<%=paymentDate%>" required>

<label>Status</label>

<select name="status">

<option value="Paid" <%= "Paid".equals(status) ? "selected" : "" %>>
Paid
</option>

<option value="Pending" <%= "Pending".equals(status) ? "selected" : "" %>>
Pending
</option>

</select>

<input type="submit" value="Update Payment">

</form>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>

<%
} else {
%>

<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

<jsp:include page="../common/header.jsp"/>

<p class="empty-state">Payment not found!</p>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>

<%
}
%>
