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
        <title>
            <% String title = (String) request.getAttribute("title");
                boolean show_result_menu = false;
                if (request.getSession().getAttribute("show_result_menu") != null) {
                    show_result_menu = (Boolean) request.getSession().getAttribute("show_result_menu");
                }
            %>
            <%=title%>
        </title>
    </head>
    <body>

        <%
            String email = (String) request.getSession().getAttribute("voter_email");
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
                        <li><a href="Controller?action=voter_home">Home</a></li>
                        <li><a href="Controller?action=list_candidates">Candidates</a></li>
                        <li><a href="Controller?action=vote_now">Vote Now!</a></li>
                            <% if (show_result_menu) {%>
                        <li><a href="Controller?action=election_result">View Results</a></li>
                            <%} else {%>
                        <li><a href="#">Result awaiting</a></li>
                            <%}%>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Welcome, <%=email%>
                            </a></li>
                        <li><a href="Controller?action=voter_logout" class="btn btn-default">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container well">
            <div class="container">
                <div class="col-lg-12">
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
                </div>

