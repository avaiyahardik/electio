<%-- 
    Document   : nomineeRegistration
    Created on : Oct 22, 2014, 2:15:32 PM
    Author     : Vishal Jain
--%>

<%@page import="DAO.DBDAOImplementation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nominee Registration</title>

        <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="../assets/css/plugins.min.css" rel="stylesheet">
        <link href="../assets/plugins/bootstrap-loading/lada.min.css" rel="stylesheet">
        <link href="../assets/css/icons/icons.min.css" rel="stylesheet">
        <link href="../assets/css/style.css" rel="stylesheet">

        <!-- BEGIN PAGE LEVEL STYLE -->
        <link href="../assets/plugins/modal/css/component.css" rel="stylesheet">
        <!-- END PAGE LEVEL STYLE -->
    </head>

    <body>
        <div class="main-content">
            <div class="row">

                <div class="col-sm-8 col-sm-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading bg-blue">
                            <h3 class="panel-title">Nominee Registration for 
                                <strong>
                                    <!-- Election Name here -->
                                    <%
                                        String name = "";
                                        long election_id = 0;
                                        if (request.getParameter("election_id") != null) {
                                            election_id = Long.parseLong(request.getParameter("election_id"));
                                            name = DBDAOImplementation.getInstance().getElectionName(election_id);
                                        }
                                    %>
                                    <%= name%>
                                </strong>

                            </h3>
                        </div>

                        <div class="panel-body">
                            <form action="NomineeRegistration" method="POST" class="form-horizontal" enctype="multipart/form-data">
                                <input type="hidden" name="election_id" value="<%= election_id%>">
                                <fieldset> 
                                    <div class="form-group">
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


                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="firstname"><strong>First Name</strong></label>
                                        <div class="col-sm-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-user"></i> 
                                                </span>
                                                <input type="text" name="firstname" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="lastname"><strong>Last Name</strong></label>
                                        <div class="col-sm-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-user"></i> 
                                                </span>
                                                <input type="text" name="lastname" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="control-label col-lg-4"><strong>Email ID</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i>@</i> 
                                                </span>
                                                <input type="text" name="email" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="mobile" class="col-lg-4 control-label"><strong>Mobile No</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-mobile"> +91</i> 
                                                </span>
                                                <input type="text" class="form-control" name="mobile" required>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="photo" class="control-label col-lg-4"><strong>Photo</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-photo"></i> 
                                                </span>
                                                <input type="file" class="form-control" name="photo" accept="image/gif, image/jpeg, image/png" required/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="requirements" class="control-label col-lg-4"><strong>Requirements File</strong> (PDF only)</label>
                                        <div class="col-lg-7">

                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-paperclip"></i> 
                                                </span>
                                                <input type="file" class="form-control" name="requirements_file"  accept="application/pdf"  required/>
                                            </div>

                                            <a href="#" class="btn btn-effect" data-modal="requirements-modal"><i class="fa fa-search"></i> View Requirements</a>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label for="organization_name" class="control-label col-lg-4"><strong>Organization Name</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-info"></i> 
                                                </span>
                                                <input type="text" class="form-control" name="organization_name" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="organization_address" class="control-label col-lg-4"><strong>Organization Address</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-envelope-o"></i> 
                                                </span>
                                                <input type="text" class="form-control" name="organization_address" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="about_organization" class="control-label col-lg-4"><strong>About Organization</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-tag"></i> 
                                                </span>
                                                <input type="text" class="form-control" name="about_organization" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password" class="control-label col-lg-4"><strong>Password</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-key"></i> 
                                                </span>
                                                <input type="password" class="form-control" name="password" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="retype_password" class="control-label col-lg-4"><strong>Retype Password</strong></label>
                                        <div class="col-lg-7">
                                            <div class="input-group">
                                                <span class="input-group-addon bg-blue">
                                                    <span class="arrow"></span>
                                                    <i class="fa fa-key"></i> 
                                                </span>
                                                <input type="password" class="form-control" name="retype_password" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-7 col-lg-offset-4">
                                            <button type="submit" name="action" value="register_nominee" class="btn btn-primary"><i class="fa fa-floppy-o"></i> File Nomination</button>
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


        <div class="md-modal md-effect-1" id="requirements-modal">
            <div class="md-content md-content-white">
                <h3>Nominee Requirements</h3>
                <div>
                    <p>A person can file a nomination for the said election, if he/she has the following eligibilities</p>
                    <p>
                        <%
                            String requirements = DBDAOImplementation.getInstance().getElectionRequirements(election_id);
                        %>
                        <%=requirements%>
                    </p>
                    <button class="btn btn-modal btn-default">Continue</button>
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
        <script src="../assets/plugins/modal/js/classie.js"></script>
        <script src="../assets/plugins/modal/js/modalEffects.js"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
    </body>
</html>
