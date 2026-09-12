<%@ page import="java.util.List" %>
<%@ page import="com.smartpg.model.Notice" %>

<%
List<Notice> notices =
        (List<Notice>) request.getAttribute("notices");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>View Notices</title>

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

<h2>Notice List</h2>

<div class="toolbar">

<a href="<%=request.getContextPath()%>/DashboardServlet">
⬅ Dashboard
</a>

<a href="<%=request.getContextPath()%>/notice/addNotice.jsp">
➕ Add New Notice
</a>

</div>

<table>

<tr>
<th>Notice ID</th>
<th>Title</th>
<th>Description</th>
<th>Notice Date</th>
<th>Action</th>
</tr>

<%
if(notices != null && !notices.isEmpty()){

for(Notice notice : notices){
%>

<tr>

<td data-label="Notice ID"><%=notice.getNoticeId()%></td>
<td data-label="Title"><%=notice.getTitle()%></td>
<td data-label="Description"><%=notice.getDescription()%></td>
<td data-label="Notice Date"><%=notice.getNoticeDate()%></td>

<td data-label="Action">

<a href="<%=request.getContextPath()%>/NoticeServlet?action=edit&id=<%=notice.getNoticeId()%>">
Edit
</a>

&nbsp;

<a href="<%=request.getContextPath()%>/NoticeServlet?action=delete&id=<%=notice.getNoticeId()%>"
onclick="return confirm('Delete this notice?');">
Delete
</a>

</td>

</tr>

<%
}

} else {
%>

<tr>
<td colspan="5" class="empty-state">No Notices Found</td>
</tr>

<%
}
%>

</table>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
