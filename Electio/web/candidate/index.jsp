<%-- 
    Document   : voterlogin
    Created on : Oct 29, 2014, 9:39:07 AM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Candidate/Nominee Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="../assets/readable/bootstrap.css" media="screen">

        <link rel="stylesheet" href="../assets/css/icons/font-awesome/css/font-awesome.min.css">
        <script src="../assets/readable/jquery-1.10.2.min.js"></script>
        <script src="../assets/readable/bootstrap.min.js"></script>

        <script type="text/JavaScript">
        </script>
    </head>
    <body>

        <%
            String election_id = request.getParameter("election_id");
            if (election_id == null || election_id.equals("")) {
        %>
        <script>
            //window.location = "index.jsp";

        </script>
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
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-12">

                    <div class="col-lg-6 col-lg-offset-3">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h1 class="panel-title">Candidate/Nominee Login</h1>
                            </div>
                            <div class="panel-body">


                                <form class="form-horizontal" action="Controller" method="POST">
                                    <div class="form-group">
                                        <label for="email" class="control-label col-sm-4">Email ID</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="email" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">

                                        <label for="password" class="control-label col-sm-4">Password</label>
                                        <div class="col-sm-7">
                                            <input type="password" name="password" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-7 col-sm-offset-4">
                                            <button type="submit" class="btn btn-info" name="action" value="candidate_login">Continue <i class="fa fa-arrow-circle-o-right"></i></button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-lg-offset-3">
                        <!-- BEGIN ERROR BOX -->
                        <%
                            String msg = (String) request.getAttribute("msg");
                            if (msg != null) {
                        %>
                        <div class="alert alert-info">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=msg%>
                        </div>
                        <% }%>

                        <%
                            String err = (String) request.getAttribute("err");
                            if (err != null) {
                        %>
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=err%>
                        </div>
                        <% }%>
                        <!-- END ERROR BOX -->
                    </div>

                </div>
            </div>


            <jsp:include page="footer.jsp"/>
