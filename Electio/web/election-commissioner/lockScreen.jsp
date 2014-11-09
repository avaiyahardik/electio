<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js sidebar-large lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js sidebar-large lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js sidebar-large lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js sidebar-large"> <!--<![endif]-->

    <head>
        <!-- BEGIN META SECTION -->
        <meta charset="utf-8">
        <title>Locked</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" href="../assets/img/favicon.png">
        <!-- END META SECTION -->
        <!-- BEGIN MANDATORY STYLE -->
        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/css/style.min.css" rel="stylesheet">
        <link href="#" rel="stylesheet" id="theme-color">
        <!-- END  MANDATORY STYLE -->
        <!-- BEGIN PAGE LEVEL STYLE -->
        <link href="../assets/css/animate-custom.css" rel="stylesheet">
        <!-- END PAGE LEVEL STYLE -->
        <script src="../assets/plugins/modernizr/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>

    <body class="login lockscreen fade-in" data-page="lockscreen">
        <!-- BEGIN LOCKSCREEN BOX -->
        <div class="container" id="login-block">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
                    <div class="login-box clearfix animated flipInY">

                        <div>
                            <i class="glyph-icon flaticon-padlock23"></i>
                        </div>
                        <%
                            String name = (String) request.getAttribute("name");
                        %>
                        <h3><%=name%></h3>
                        <hr />
                        <div class="login-form">


                            <form action="Controller" method="POST">
                                <input type="hidden" name="old_action" value="<%= request.getAttribute("old_action")%>">
                                <input type="hidden" name="name" value="<%= request.getAttribute("name")%> ">
                                <div class="col-md-12 form-input">
                                    <input type="password" name="password" class="input-field form-control width-100p password" placeholder="Password" required/>
                                </div>
                                <button type="submit" class="btn btn-login btn-reset" name="action" value="unlock">Unlock</button>
                            </form>
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
                            <div class="login-links">
                                <a href="Controller?action=logout_election_commissioner">Sign in using another account</a> 
                            </div>
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
        <script src="../assets/js/account.js"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
    </body>

</html>
