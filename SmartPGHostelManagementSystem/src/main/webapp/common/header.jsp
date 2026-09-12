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

🏢 Smart PG Hostel

</div>





<div class="nav">



<a href="<%=request.getContextPath()%>/DashboardServlet">

Dashboard

</a>




<a href="<%=request.getContextPath()%>/StudentServlet">

Students

</a>




<a href="<%=request.getContextPath()%>/RoomServlet">

Rooms

</a>




<a href="<%=request.getContextPath()%>/RoomAllocationServlet">

Allocations

</a>




<a href="<%=request.getContextPath()%>/payment/viewPayments.jsp">

Payments

</a>




<a href="<%=request.getContextPath()%>/ComplaintServlet">

Complaints

</a>




<a href="<%=request.getContextPath()%>/NoticeServlet?action=list">

Notices

</a>




<a href="<%=request.getContextPath()%>/VisitorServlet?action=list">

Visitors

</a>




<a class="logout"
href="<%=request.getContextPath()%>/LogoutServlet">

Logout

</a>



</div>



</div>



<br>
