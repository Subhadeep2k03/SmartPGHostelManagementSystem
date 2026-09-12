<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Notice</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

textarea{
    resize:vertical;
    height:120px;
}

</style>

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/NoticeServlet?action=list">⬅ Back to Notices</a>
</div>

<h2>Add Notice</h2>

<form action="<%=request.getContextPath()%>/NoticeServlet" method="post">

<label>Title</label>
<input type="text" name="title" required>

<label>Description</label>
<textarea name="description" required></textarea>

<label>Notice Date</label>
<input type="date" name="noticeDate" required>

<button type="submit">
Save Notice
</button>

</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
