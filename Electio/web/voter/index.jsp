<%-- 
    Document   : voterlogin
    Created on : Oct 29, 2014, 9:39:07 AM
    Author     : Vishal Jain
--%>

<%@page import="java.util.Date"%>
<%@page import="Model.Election"%>
<%@page import="DAO.DBDAOImplElection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voter Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="../assets/readable/bootstrap.css" media="screen">

        <link rel="stylesheet" href="../assets/css/icons/font-awesome/css/font-awesome.min.css">
        <script src="../assets/readable/jquery-1.10.2.min.js"></script>
        <script src="../assets/readable/bootstrap.min.js"></script>

        <script type="text/JavaScript">
        </script>
    </head>
    <body>

        <% String name = "";
            DBDAOImplElection objE = DBDAOImplElection.getInstance();
            Election election = null;
            String elec_id = request.getParameter("election_id");
            long election_id = 0;
            if (elec_id != null) {
                election_id = Long.parseLong(elec_id);
                name = objE.getElectionName(election_id);
                election = objE.getElection(election_id);
            } else {%>
        <script>
            window.location = "../index.jsp?err=<%= (String) request.getAttribute("err")%>";
        </script>
        <%
            }
        %>
        <%if (name == null) {%>
        <script>
            window.location = "../index.jsp?err=Invalid election login link";
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
                                <h1 class="panel-title">Voter Login for <strong>"<%= name%>"</strong></h1>
                            </div>
                            <div class="panel-body">


                                <form class="form-horizontal" action="Controller" method="POST">
                                    <input type="hidden" name="election_id" value="<%=elec_id%>">
                                    <input type="hidden" name="step" value="1">
                                    <div class="form-group">
                                        <label for="email" class="control-label col-sm-4">Email ID</label>
                                        <div class="col-sm-7">
                                            <% if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {%>
                                            <input type="email" value="<%= request.getParameter("email")%>" name="email" class="form-control" required pattern="[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$" title="Enter a valid email address">
                                            <%
                                            } else {%>
                                            <input type="email" name="email" class="form-control" required pattern="[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$" title="Enter a valid email address">
                                            <%}%>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-7 col-sm-offset-4">
                                            <button type="submit" class="btn btn-info" name="action" value="voter_login">Continue <i class="fa fa-arrow-circle-o-right"></i></button>
                                        </div></div>
                                </form>

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-lg-offset-3">
                        <!-- BEGIN ERROR BOX --> 
                        <div class="col-lg-12">
                            <% String err = (String) request.getAttribute("err");
                                String err1 = (String) request.getParameter("err");
                                if (err
                                        != null && !err.equals(
                                                "") && !err.equals("null")) {%>
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=err%>
                            </div>
                            <% } else if (err1
                                    != null && !err1.equals(
                                            "") && !err1.equals("null")) {%>
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=err1%>
                            </div>
                            <%}
                                String msg = (String) request.getAttribute("msg");
                                String msg1 = (String) request.getParameter("msg");
                                if (msg
                                        != null && !msg.equals(
                                                "") && !msg.equals("null")) {%>
                            <div class="alert alert-info">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=msg%>
                            </div>
                            <%} else if (msg1
                                    != null && !msg1.equals(
                                            "") && !msg1.equals("null")) {
                            %>
                            <div class="alert alert-info">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=msg1%>
                            </div>
                            <%}%>
                        </div>
                        <!-- END ERROR BOX --> 
                    </div>

                </div>
            </div>


            <jsp:include page="footer.jsp"/>
