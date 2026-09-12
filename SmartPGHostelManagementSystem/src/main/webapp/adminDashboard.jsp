<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // Defensive fallback: if this page is ever opened without going
    // through DashboardServlet first, show 0 instead of the literal
    // text "null" for every summary count.
    Object students = request.getAttribute("students");
    Object rooms = request.getAttribute("rooms");
    Object payments = request.getAttribute("payments");
    Object complaints = request.getAttribute("complaints");
    Object totalVisitors = request.getAttribute("totalVisitors");
    Object totalNotices = request.getAttribute("totalNotices");

    if (students == null || rooms == null || payments == null
            || complaints == null || totalVisitors == null || totalNotices == null) {
        response.sendRedirect(request.getContextPath() + "/DashboardServlet");
        return;
    }
%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Admin Dashboard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

.container{

    width:100%;
    margin:10px auto 0;
    text-align:center;

}

h1{

    margin:6px 0 4px;
    font-size:26px;

}

.welcome-sub{

    color:var(--text-muted);
    margin-bottom:10px;
    font-size:14.5px;

}

.card-container{

    display:flex;
    justify-content:center;
    flex-wrap:wrap;
    gap:4px;

}

.card{

    width:210px;
    padding:24px 20px;
    margin:12px;
    background:var(--surface);
    border-radius:var(--radius);
    box-shadow:var(--shadow);
    border:1px solid var(--border);
    transition:transform .25s ease, box-shadow .25s ease;

}

.card:hover{

    transform:translateY(-8px);
    box-shadow:var(--shadow-lg);

}

.card a{

    text-decoration:none;
    color:var(--text);

}

.icon{

    font-size:36px;
    margin-bottom:8px;

}

.count{

    font-size:34px;
    font-weight:700;
    font-family:'Poppins','Inter',Arial,sans-serif;
    color:var(--primary);

}

.summary-card{ border-top:5px solid var(--primary); }
.summary-card:nth-child(2){ border-top-color:#4f46e5; }
.summary-card:nth-child(3){ border-top-color:#16a34a; }
.summary-card:nth-child(4){ border-top-color:#f59e0b; }
.summary-card:nth-child(5){ border-top-color:#dc2626; }
.summary-card:nth-child(6){ border-top-color:#6f42c1; }
.summary-card:nth-child(7){ border-top-color:#06b6d4; }

h3{

    margin:10px 0 0;
    font-size:15.5px;

}

.section-title{

    width:100%;
    margin-top:36px;
    margin-bottom:6px;
    color:var(--text);
    text-align:left;
    font-size:19px;

}

.management-card{

    background:var(--surface);

}

.management-card:hover{

    background:linear-gradient(120deg, var(--primary), var(--primary-dark));
    border-color:var(--primary);

}

.management-card:hover a{

    color:white;

}

</style>


</head>



<body>



<jsp:include page="common/header.jsp"/>




<div class="container">



<h1>

Welcome, <%=session.getAttribute("username")%> 👋

</h1>

<p class="welcome-sub">Here's what's happening at your PG hostel today.</p>




<div class="card-container">



<h2 class="section-title">

📊 Dashboard Summary

</h2>




<div class="card summary-card">
<div class="icon">👨‍🎓</div>
<h3>Total Students</h3>
<div class="count"><%=request.getAttribute("students")%></div>
</div>

<div class="card summary-card">
<div class="icon">🏠</div>
<h3>Total Rooms</h3>
<div class="count"><%=request.getAttribute("rooms")%></div>
</div>

<div class="card summary-card">
<div class="icon">💰</div>
<h3>Total Payments</h3>
<div class="count"><%=request.getAttribute("payments")%></div>
</div>

<div class="card summary-card">
<div class="icon">📝</div>
<h3>Total Complaints</h3>
<div class="count"><%=request.getAttribute("complaints")%></div>
</div>

<div class="card summary-card">
<div class="icon">🧑‍🤝‍🧑</div>
<h3>Total Visitors</h3>
<div class="count"><%=request.getAttribute("totalVisitors")%></div>
</div>

<div class="card summary-card">
<div class="icon">📢</div>
<h3>Total Notices</h3>
<div class="count"><%=request.getAttribute("totalNotices")%></div>
</div>




<h2 class="section-title">

⚙ Management

</h2>




<div class="card management-card">
<a href="<%=request.getContextPath()%>/StudentServlet">
<div class="icon">👨‍🎓</div>
<h3>Manage Students</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/RoomServlet">
<div class="icon">🏠</div>
<h3>Manage Rooms</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/room/addRoom.jsp">
<div class="icon">➕</div>
<h3>Add Room</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/RoomAllocationServlet">
<div class="icon">🔑</div>
<h3>Room Allocation</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/payment/viewPayments.jsp">
<div class="icon">💳</div>
<h3>Payments</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/ComplaintServlet">
<div class="icon">📋</div>
<h3>View Complaints</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/complaint/addComplaint.jsp">
<div class="icon">➕</div>
<h3>Add Complaint</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/NoticeServlet?action=list">
<div class="icon">📢</div>
<h3>Manage Notices</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/notice/addNotice.jsp">
<div class="icon">➕</div>
<h3>Add Notice</h3>
</a>
</div>

<div class="card management-card">
<a href="<%=request.getContextPath()%>/VisitorServlet?action=list">
<div class="icon">🧑‍🤝‍🧑</div>
<h3>Manage Visitors</h3>
</a>
</div>



</div>



</div>



<jsp:include page="common/footer.jsp"/>



</body>

</html>
