<%@ page import="com.smartpg.model.Student"%>

<%
Student student = (Student) request.getAttribute("student");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Student</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

.buttons{
    margin-top:18px;
    display:flex;
    gap:10px;
}

.buttons input[type="submit"]{
    margin-top:0;
}

.back-btn{
    flex:1;
    display:flex;
    align-items:center;
    justify-content:center;
    background:#eef0ff;
    color:var(--primary-dark);
    border-radius:var(--radius-sm);
    text-decoration:none;
    font-weight:600;
    transition:all .15s ease;
}

.back-btn:hover{
    background:var(--border);
}

</style>

</head>


<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/StudentServlet">⬅ Back to Students</a>
</div>

<h2>Edit Student</h2>


<form
    action="${pageContext.request.contextPath}/StudentServlet"
    method="post">


    <!-- Student ID -->

    <input
        type="hidden"
        name="studentId"
        value="<%= student.getStudentId() %>">


    <!-- Action -->

    <input
        type="hidden"
        name="action"
        value="update">


    <label>First Name</label>

    <input
        type="text"
        name="firstName"
        value="<%= student.getFirstName() %>"
        required>


    <label>Last Name</label>

    <input
        type="text"
        name="lastName"
        value="<%= student.getLastName() %>">


    <label>Phone</label>

    <input
        type="text"
        name="phone"
        value="<%= student.getPhone() %>"
        required>


    <label>Email</label>

    <input
        type="email"
        name="email"
        value="<%= student.getEmail() %>"
        required>


    <label>Address</label>

    <input
        type="text"
        name="address"
        value="<%= student.getAddress() %>">


    <label>Status</label>

    <input
        type="text"
        name="status"
        value="<%= student.getStatus() %>">


    <div class="buttons">


        <input
            type="submit"
            value="Update Student">


        <a
            href="${pageContext.request.contextPath}/StudentServlet"
            class="back-btn">

            Cancel

        </a>


    </div>


</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
