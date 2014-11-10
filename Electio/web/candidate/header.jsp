<%-- 
    Document   : sidebar
    Created on : Oct 30, 2014, 5:19:00 PM
    Author     : Vishal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../assets/readable/bootstrap.css" media="screen">  

        <link rel="stylesheet" href="../assets/css/icons/font-awesome/css/font-awesome.min.css">
        <script src="../assets/readable/jquery-1.10.2.min.js"></script>
        <script src="../assets/readable/bootstrap.min.js"></script>
        <title><% String title = (String) request.getAttribute("title");
            int election_id = Integer.parseInt((String) request.getSession().getAttribute("election_id"));
            %>
            <%=title%></title>
    </head>
    <body>

        <%
            String email = (String) request.getSession().getAttribute("candidate_email");
            if (email == null || email.equals("")) {
        %>
        <script>window.location = "index.jsp";</script>
        <%
            }
        %>
        <div class="navbar navbar-default ">
            <div class="container">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">Electio</a>
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav">
                        <li><a href="Controller?action=candidate_home">Home</a></li>
                        <li><a href="Controller?action=candidate_profile">Profile</a></li>
                        <li><a href="Controller?action=election_result&election_id=<%=election_id%>">View Results</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <%
                            String name = (String) request.getSession().getAttribute("candidate_name");
                        %>
                        <li><a href="#">Welcome, <%=name%>
                            </a></li>
                        <li><a href="Controller?action=candidate_logout" class="btn btn-default">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container">

            <!-- BEGIN ERROR BOX --> 
            <div class="col-lg-12">
                <% String err = (String) request.getAttribute("err");
                    String err1 = (String) request.getParameter("err");
                    if (err != null && !err.equals("") && !err.equals("null")) {%>
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <%=err%>
                </div>
                <% } else if (err1 != null && !err1.equals("") && !err1.equals("null")) {%>
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <%=err1%>
                </div>
                <%}
                    String msg = (String) request.getAttribute("msg");
                    String msg1 = (String) request.getParameter("msg");
                    if (msg != null && !msg.equals("") && !msg.equals("null")) {%>
                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <%=msg%>
                </div>
                <%} else if (msg1 != null && !msg1.equals("") && !msg1.equals("null")) {
                %>
                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <%=msg1%>
                </div>
                <%}%>
            </div>
            <!-- END ERROR BOX --> 
