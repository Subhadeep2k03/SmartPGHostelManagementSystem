<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<style>

.header{

    padding:16px 26px;
    color:white;
    border-radius:var(--radius, 14px);
    display:flex;
    justify-content:space-between;
    align-items:center;
    flex-wrap:wrap;
    gap:10px;

}

.logo{

    font-size:20px;
    font-weight:700;
    display:flex;
    align-items:center;
    gap:8px;
    letter-spacing:-0.01em;

}

.nav{

    display:flex;
    align-items:center;
    flex-wrap:wrap;

}

.nav a{

    color:white;
    text-decoration:none;
    font-weight:600;
    font-size:14px;
    margin-left:8px;
    padding:9px 14px;
    border-radius:999px;

}

.nav a:hover{

    background:rgba(255,255,255,0.16);

}

.logout{

    background:#dc3545;
    margin-left:16px !important;

}

.logout:hover{

    background:#b52a37 !important;
    color:white !important;

}

</style>


<div class="header">

<div class="logo">

🏠 Smart PG Hostel — Student Portal

</div>


<div class="nav">

<a href="<%=request.getContextPath()%>/student/StudentDashboardServlet">
Dashboard
</a>

<a href="<%=request.getContextPath()%>/student/StudentProfileServlet">
My Profile
</a>

<a href="<%=request.getContextPath()%>/student/StudentAllocationServlet">
My Room
</a>

<a href="<%=request.getContextPath()%>/student/StudentRoomServlet">
Available Rooms
</a>

<a href="<%=request.getContextPath()%>/student/StudentPaymentServlet">
My Payments
</a>

<a href="<%=request.getContextPath()%>/student/StudentVisitorServlet">
My Visitors
</a>

<a href="<%=request.getContextPath()%>/student/StudentComplaintServlet">
My Complaints
</a>

<a href="<%=request.getContextPath()%>/student/StudentNoticeServlet">
Notices
</a>

<a class="logout"
href="<%=request.getContextPath()%>/student/StudentLogoutServlet">
Logout
</a>

</div>

</div>

<br>
