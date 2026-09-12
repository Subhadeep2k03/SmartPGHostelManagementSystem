<%@ page import="java.util.List" %>
<%@ page import="com.smartpg.model.Complaint" %>

<%
List<Complaint> complaints =
        (List<Complaint>) request.getAttribute("complaints");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>View Complaints</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="<%=request.getContextPath()%>/complaint/addComplaint.jsp">
➕ Add New Complaint
</a>

</div>

<h2>Complaint List</h2>

<table>

<tr>
    <th>Complaint ID</th>
    <th>Student ID</th>
    <th>Room ID</th>
    <th>Complaint Date</th>
    <th>Description</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<%
if (complaints != null && !complaints.isEmpty()) {

    for (Complaint complaint : complaints) {
%>

<tr>

    <td data-label="Complaint ID"><%= complaint.getComplaintId() %></td>
    <td data-label="Student ID"><%= complaint.getStudentId() %></td>
    <td data-label="Room ID"><%= complaint.getRoomId() %></td>
    <td data-label="Complaint Date"><%= complaint.getComplaintDate() %></td>
    <td data-label="Description"><%= complaint.getDescription() %></td>
    <td data-label="Status"><%= complaint.getStatus() %></td>

    <td data-label="Action">

        <a href="<%=request.getContextPath()%>/ComplaintServlet?action=edit&id=<%= complaint.getComplaintId() %>">
            Edit
        </a>

        &nbsp;

        <a href="<%=request.getContextPath()%>/ComplaintServlet?action=delete&id=<%= complaint.getComplaintId() %>"
           onclick="return confirm('Delete this complaint?');">
            Delete
        </a>

    </td>

</tr>

<%
    }
} else {
%>

<tr>
    <td colspan="7" class="empty-state">No Complaints Found</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>
