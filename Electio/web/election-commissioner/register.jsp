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
                            <form action="" method="POST" class="form-horizontal">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="first_name"><strong>First Name</strong></label>
                                        <div class="col-sm-7">
                                            <input type="text" name="fisrt_name" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-3" for="last_name"><strong>Last Name</strong></label>
                                        <div class="col-sm-7">
                                            <input type="text" name="last_name" class="form-control" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="control-label col-lg-3"><strong>Email ID</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" name="email" class="form-control" required>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="mobile-no" class="col-lg-3 control-label"><strong>Mobile No</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i>+91</i> 
                                                </span>
                                                <input type="text" class="form-control" name="mobile_no" required>
                                            </div>
                                        </div>
                                    </div>

                                </fieldset>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
