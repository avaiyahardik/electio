<%-- 
    Document   : index
    Created on : Oct 18, 2014, 9:53:40 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js sidebar-large lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js sidebar-large lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js sidebar-large lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js sidebar-large"> <!--<![endif]-->

    <head>
        <!-- BEGIN META SECTION -->
        <meta charset="utf-8">
        <title>Election Commissioner Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" href="assets/img/favicon.png">
        <!-- END META SECTION -->
        <!-- BEGIN MANDATORY STYLE -->


        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/plugins/bootstrap-loading/lada.min.css" rel="stylesheet">
        <link href="../assets/css/style.css" rel="stylesheet">
        <link href="#" rel="stylesheet" id="theme-color">
        <!-- END  MANDATORY STYLE -->
        <!-- BEGIN PAGE LEVEL STYLE -->
        <link href="../assets/css/animate-custom.css" rel="stylesheet">
        <!-- END PAGE LEVEL STYLE -->
        <script src="assets/plugins/modernizr/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    <body class="login fade-in" data-page="login">
        <!-- BEGIN LOGIN BOX -->
        <div class="container" id="login-block">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
                    <div class="login-box clearfix animated flipInY">
                        <div class="page-icon animated bounceInDown">
                            <img src="../assets/img/account/user-icon.png" alt="Key icon">
                        </div>
                        <hr>
                        <div class="login-form">
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


                            <form action="Controller" method="post">
                                <input type="text" name="email" placeholder="Email" class="input-field form-control user" required/>
                                <input type="password" name="password" placeholder="Password" class="input-field form-control password" required/>
                                <button type="submit"  name="action" value="election_commissioner_login" class="btn btn-login ladda-button" data-style="expand-left"><span class="ladda-label">login</span></button>
                            </form>
                            <div class="login-links">
                                <a href="forgotPassword.jsp">Forgot password?</a>
                                <br>
                                <a href="../registration.jsp">Don't have an account? <strong>Sign Up</strong></a>

                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- END LOCKSCREEN BOX -->
        <!-- BEGIN MANDATORY SCRIPTS -->
        <script src="../assets/plugins/jquery-1.11.js"></script>
        <script src="../assets/plugins/jquery-migrate-1.2.1.js"></script>
        <script src="../assets/plugins/jquery-ui/jquery-ui-1.10.4.min.js"></script>
        <script src="../assets/plugins/jquery-mobile/jquery.mobile-1.4.2.js"></script>
        <script src="../assets/plugins/bootstrap/bootstrap.min.js"></script>
        <script src="../assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
        <!-- END MANDATORY SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="../assets/plugins/backstretch/backstretch.min.js"></script>
        <script src="../assets/plugins/bootstrap-loading/lada.min.js"></script>
        <script src="../assets/js/account.js"></script>
        <!-- END PAGE LEVEL SCRIPTS -->s
    </body>
</html>