<%-- 
    Document   : header
    Created on : Oct 28, 2014, 6:32:38 PM
    Author     : Vishal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="assets/readable/bootstrap.css" media="screen">  
        
        <link rel="stylesheet" href="assets/css/icons/font-awesome/css/font-awesome.min.css">
        <script src="assets/readable/jquery-1.10.2.min.js"></script>
        <script src="assets/readable/bootstrap.min.js"></script>
    </head>
    <body>

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
                        <li><a href="about.jsp">About</a></li>
                        <li><a href="disclaimer.jsp">Disclaimer</a></li>
                        <li><a href="privacy.jsp">Privacy</a></li>
                        <li><a href="contact.jsp">Contact</a></li>
                    </ul>

                    <form class="navbar-form navbar-right"  action="election-commissioner/Controller" method="post">
                        <div class="form-group">
                            <input type="text" name="email" class="form-control" placeholder="Email ID"/><br/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Password"/><br/>
                        </div>

                        <div class="btn-group">
                            <button type="submit" class="btn btn-primary"   name="action" value="election_commissioner_login">Login</button>
                            <button type="button" class="btn btn-primary dropdown-toggle" 
                                    data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="election-commissioner/registration.jsp">Register</a></li>
                                <li><a href="election-commissioner/forgotPassword.jsp">Forgot Password?</a></li>
                            </ul>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        
        <div class="container">
