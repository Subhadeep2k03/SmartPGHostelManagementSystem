<%@ page import="java.util.List"%>
<%@ page import="com.smartpg.model.Student"%>

<%
List<Student> students =
        (List<Student>) request.getAttribute("students");

String message =
        (String) session.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manage Students</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<jsp:include page="../common/header.jsp"/>

<!-- SUCCESS MESSAGE -->
<%
if (message != null) {
%>

<div class="alert alert-success">
    <%= message %>
</div>

<%
    session.removeAttribute("message");
}
%>


<!-- Navigation -->

<div class="toolbar">

    <a href="<%=request.getContextPath()%>/DashboardServlet">
        ⬅ Dashboard
    </a>

    <a href="<%=request.getContextPath()%>/student/addStudent.jsp">
        ➕ Add Student
    </a>

</div>


<h2>Manage Students</h2>


<%
if (students != null && !students.isEmpty()) {
%>

<table>

    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Address</th>
        <th>Status</th>
        <th>Action</th>
    </tr>


<%
for (Student student : students) {
%>

    <tr>

        <td data-label="ID">
            <%= student.getStudentId() %>
        </td>

        <td data-label="First Name">
            <%= student.getFirstName() %>
        </td>

        <td data-label="Last Name">
            <%= student.getLastName() %>
        </td>

        <td data-label="Phone">
            <%= student.getPhone() %>
        </td>

        <td data-label="Email">
            <%= student.getEmail() %>
        </td>

        <td data-label="Address">
            <%= student.getAddress() %>
        </td>

        <td data-label="Status">
            <%= student.getStatus() %>
        </td>

        <td data-label="Action">

            <!-- EDIT -->

            <a href="<%=request.getContextPath()%>/EditStudentServlet?id=<%=student.getStudentId()%>">
                Edit
            </a>

            &nbsp;

            <!-- DELETE -->

            <a href="<%=request.getContextPath()%>/DeleteStudentServlet?id=<%=student.getStudentId()%>"
               onclick="return confirm('Are you sure you want to delete this student?');">
                Delete
            </a>

        </td>

    </tr>

<%
}
%>

</table>


<%
} else {
%>

<p class="empty-state">No Student Found</p>

<%
}
%>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>
