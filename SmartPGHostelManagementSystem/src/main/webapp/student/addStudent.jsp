<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Student</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>

<jsp:include page="../common/header.jsp"/>

<div class="toolbar">
    <a href="<%=request.getContextPath()%>/StudentServlet">⬅ Back to Students</a>
</div>

<h2>Add Student</h2>

<form action="<%=request.getContextPath()%>/AddStudentServlet" method="post">

<label>First Name</label>
<input type="text" name="firstName" required>

<label>Last Name</label>
<input type="text" name="lastName" required>

<label>Gender</label>
<select name="gender">

<option>Male</option>
<option>Female</option>
<option>Other</option>

</select>

<label>Date of Birth</label>
<input type="date" name="dob" required>

<label>Phone</label>
<input type="text" name="phone" required>

<label>Email</label>
<input type="email" name="email" required>

<label>Address</label>
<input type="text" name="address" required>

<label>Guardian Name</label>
<input type="text" name="guardianName" required>

<label>Guardian Phone</label>
<input type="text" name="guardianPhone" required>

<label>Joining Date</label>
<input type="date" name="joinDate" required>

<label>Status</label>
<select name="status">

<option>Active</option>
<option>Inactive</option>

</select>

<button type="submit">

Save Student

</button>

</form>

<jsp:include page="../common/footer.jsp"/>

</body>

</html>
