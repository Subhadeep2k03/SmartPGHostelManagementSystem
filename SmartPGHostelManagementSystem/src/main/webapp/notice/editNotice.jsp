<%@ page import="com.smartpg.model.Notice" %>

<%
Notice notice = (Notice) request.getAttribute("notice");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Notice</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

textarea{
    height:120px;
    resize:vertical;
}

</style>

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/NoticeServlet?action=list">⬅ Back to Notices</a>
</div>

<%
if (notice == null) {
%>

<p class="empty-state">Notice not found!</p>

<%
} else {
%>

<h2>Edit Notice</h2>

<form action="<%=request.getContextPath()%>/NoticeServlet" method="post">

<input type="hidden" name="noticeId" value="<%=notice.getNoticeId()%>">

<label>Title</label>
<input type="text" name="title" value="<%=notice.getTitle()%>" required>

<label>Description</label>
<textarea name="description" required><%=notice.getDescription()%></textarea>

<label>Notice Date</label>
<input type="date" name="noticeDate" value="<%=notice.getNoticeDate()%>" required>

<button type="submit">
Update Notice
</button>

</form>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
