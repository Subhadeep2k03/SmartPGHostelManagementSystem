<%@ page import="java.util.List"%>
<%@ page import="com.smartpg.model.Visitor"%>

<%
List<Visitor> visitors =
        (List<Visitor>) request.getAttribute("visitors");

String message =
        (String) session.getAttribute("message");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Visitor List</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<%
if(message != null){
%>

<div class="alert alert-success">
    <%=message%>
</div>

<%
session.removeAttribute("message");
}
%>

<h2>Visitor List</h2>

<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="<%=request.getContextPath()%>/visitor/addVisitor.jsp">
➕ Add Visitor
</a>

</div>

<table>

<tr>
<th>ID</th>
<th>Student ID</th>
<th>Visitor Name</th>
<th>Phone</th>
<th>Relation</th>
<th>Visit Date</th>
<th>In Time</th>
<th>Out Time</th>
<th>Action</th>
</tr>

<%
if(visitors != null && !visitors.isEmpty()){

for(Visitor v : visitors){
%>

<tr>

<td data-label="ID"><%=v.getVisitorId()%></td>
<td data-label="Student ID"><%=v.getStudentId()%></td>
<td data-label="Visitor Name"><%=v.getVisitorName()%></td>
<td data-label="Phone"><%=v.getPhone()%></td>
<td data-label="Relation"><%=v.getRelation()%></td>
<td data-label="Visit Date"><%=v.getVisitDate()%></td>
<td data-label="In Time"><%=v.getInTime()%></td>
<td data-label="Out Time"><%=v.getOutTime()%></td>

<td data-label="Action">

<a href="<%=request.getContextPath()%>/VisitorServlet?action=edit&id=<%=v.getVisitorId()%>">
Edit
</a>

&nbsp;

<a href="<%=request.getContextPath()%>/VisitorServlet?action=delete&id=<%=v.getVisitorId()%>"
onclick="return confirm('Delete this visitor?');">
Delete
</a>

</td>

</tr>

<%
}

} else {
%>

<tr>
<td colspan="9" class="empty-state">No Visitors Found</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
