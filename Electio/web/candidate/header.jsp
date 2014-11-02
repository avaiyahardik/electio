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
        <title><% String title = (String) request.getAttribute("title");%>
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
                        <li><a href="Controller?action=view_result">View Results</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <%
                            String name = (String) request.getAttribute("candidate_name");
                        %>
                        <li><a href="#">Welcome, <%=name%>
                            </a></li>
                        <li><a href="Controller?action=candidate_logout" class="btn btn-default">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container">

