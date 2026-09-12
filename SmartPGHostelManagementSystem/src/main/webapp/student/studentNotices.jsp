<%@page import="java.util.List"%>
<%@page import="com.smartpg.model.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Notices</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/studentHeader.jsp"/>

<div class="toolbar">
<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">⬅ Dashboard</a>
</div>

<h2>Notices</h2>

<%
List<Notice> notices = (List<Notice>) request.getAttribute("notices");

if (notices != null && !notices.isEmpty()) {

    for (Notice n : notices) {
%>

<div class="panel">
<h3 style="margin-top:0;"><%=n.getTitle()%></h3>
<p class="text-muted" style="margin:-6px 0 10px;"><%=n.getNoticeDate()%></p>
<p style="margin:0;"><%=n.getDescription()%></p>
</div>

<%
    }
} else {
%>

<div class="empty-state">No notices at the moment.</div>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
