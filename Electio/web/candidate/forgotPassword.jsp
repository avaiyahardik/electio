<%-- 
    Document   : password
    Created on : Oct 20, 2014, 5:42:15 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- BEGIN META SECTION -->
        <meta charset="utf-8">
        <title>Forgot Password</title>
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

                        <hr>
                        <div class="login-form">
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


                            <form action="Controller" method="post">
                                <input type="text" name="email" placeholder="Email" class="input-field form-control user" required/>                        
                                <button type="submit"  name="action" value="candidate_reset_password" class="btn btn-login ladda-button" data-style="expand-left"><span class="ladda-label">Reset</span></button>
                            </form>
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
        <!-- 
            Display msg and err from request object as we shown in every jsp pages.
            On successfully sending new password to election commission msg will be set to "Password sent successfully to <email id>"
            If system fails to send password to given email err will be set to "Fail to send email at <email>"
        
            Create form with following elements and give action="Controller" method="POST"
            Create text box named "email" to take email of election commissioner.
            Create submit button named "action" as "Send Password" give value="reset_password" -->
    </body>
</html>
