<%-- 
    Document   : register
    Created on : Oct 18, 2014, 10:44:35 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election Commissioner Registration</title>

        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/plugins/bootstrap-loading/lada.min.css" rel="stylesheet">
        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">

                <div class="col-sm-8 col-sm-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading bg-blue">
                            <h3 class="panel-title">Election Commissioner Registration</h3>
                        </div>

                        <div class="panel-body">
                            <form action="Controller" method="POST" class="form-horizontal">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="firstname"><strong>First Name</strong></label>
                                        <div class="col-sm-7">
                                            <input type="text" name="firstname" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="lastname"><strong>Last Name</strong></label>
                                        <div class="col-sm-7">
                                            <input type="text" name="lastname" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="control-label col-lg-4"><strong>Email ID</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" name="email" class="form-control" required>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="mobile" class="col-lg-4 control-label"><strong>Mobile No</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i>+91</i> 
                                                </span>
                                                <input type="text" class="form-control" name="mobile" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="organization" class="control-label col-lg-4"><strong>Organization</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="organization" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password" class="control-label col-lg-4"><strong>Password</strong></label>
                                        <div class="col-lg-7">
                                            <input type="password" class="form-control" name="password" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="retype_password" class="control-label col-lg-4"><strong>Retype Password</strong></label>
                                        <div class="col-lg-7">
                                            <input type="password" class="form-control" name="retype_password" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-7 col-lg-offset-4">
                                            <button type="submit" name="action" value="election_commissioner_registration" class="btn btn-primary"><i class="fa fa-check"></i> Register</button>
                                            <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Reset</button>
                                        </div>
                                    </div>
                                </fieldset>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>


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
        <!-- END PAGE LEVEL SCRIPTS -->

        <script>
            $(function() {
                $('#submit-form').click(function(e) {
                    e.preventDefault();
                    var l = Ladda.create(this);
                    l.start();
                    setTimeout(function() {
                        window.location.href = "index.html";
                    }, 2000);

                });
            });
        </script>
    </body>
</html>
