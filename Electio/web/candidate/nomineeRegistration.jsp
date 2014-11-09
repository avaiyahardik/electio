<%-- 
    Document   : nomineeRegistration
    Created on : Oct 22, 2014, 2:15:32 PM
    Author     : Vishal Jain
--%>

<%@page import="Model.Election"%>
<%@page import="Model.Organization"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="java.util.Date"%>
<%@page import="DAO.DBDAOImplElection"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nominee Registration</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../assets/readable/bootstrap.css" media="screen">  

        <link rel="stylesheet" href="../assets/css/icons/font-awesome/css/font-awesome.min.css">
        <script src="../assets/readable/jquery-1.10.2.min.js"></script>
        <script src="../assets/readable/bootstrap.min.js"></script>
        <script src="script.js"></script>
    </head>

    <body>

        <div class="navbar navbar-default ">
            <div class="container">
                <div class="navbar-header">
                    <a href="#" class="navbar-brand">Electio</a>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading bg-blue">
                            <span class="panel-title">Nominee Registration for
                                <!-- Election Name here -->
                                <% String name = "";
                                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                                    Election election = null;
                                    long election_id = 0;
                                    if (request.getParameter("election_id") != null) {
                                        election_id = Long.parseLong(request.getParameter("election_id"));
                                        name = objE.getElectionName(election_id);
                                        election = objE.getElection(election_id);
                                    } else {%>
                                <script>
                                    window.location = "../index.jsp";

                                </script>
                                <%
                                    }
                                %>
                                <%if (name == null) {%>
                                <script>
                                    window.location = "../index.jsp?err=Invalid election link";

                                </script>
                                <%
                                    }
                                %>
                                <%if (election != null && election.getNomination_start().after(new Date())) {%>
                                <script>
                                    window.location = "../index.jsp?err=Nomination has not been started for this election";
                                </script>
                                <%
                                    }
                                %>
                                <%if (election != null && election.getNomination_end().before(new Date())) {%>
                                <script>
                                    window.location = "../index.jsp?err=Nomination period got over for this election";

                                </script>
                                <%
                                    }
                                %>
                                <strong><%= name%></strong>
                            </span>
                        </div>

                        <div class="panel-body">
                            <form action="NomineeRegistration" method="POST" class="form-horizontal" enctype="multipart/form-data">
                                <input type="hidden" name="election_id" value="<%= election_id%>">
                                <fieldset> 
                                    <!-- BEGIN ERROR BOX -->
                                    <div class="form-group col-lg-12">
                                        <% String msg = (String) request.getAttribute("msg");
                                            if (msg != null) {%>

                                        <div class="alert alert-info">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            <%=msg%>
                                        </div>
                                        <% }%>
                                        <% String err = (String) request.getAttribute("err");
                                            if (err != null) {%>
                                        <div class="alert alert-danger">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            <%=err%>
                                        </div>
                                        <% }%>
                                    </div>
                                    <!-- END ERROR BOX -->  

                                    <div class="form-group">
                                        <label for="email" class="control-label col-lg-4"><strong>Email ID</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" name="email" class="form-control" required id="email">
                                        </div>
                                    </div>

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
                                        <label for="gender" class="control-label col-lg-4"><strong>Gender</strong></label>
                                        <div class="col-sm-7">
                                            <select name="gender" class="form-control">
                                                <option value="0">Male</option>
                                                <option value="1">Female</option>
                                                <option value="2">Other</option>
                                            </select>
                                        </div> 
                                    </div>

                                    <div class="form-group">
                                        <label for="mobile" class="col-lg-4 control-label"><strong>Mobile No</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="mobile" required>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="photo" class="control-label col-lg-4"><strong>Photo</strong></label>
                                        <div class="col-lg-7">
                                            <input type="file" class="form-control" name="photo" accept="image/gif, image/jpeg, image/png" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="requirements" class="control-label col-lg-4"><strong>Requirements File</strong> (PDF only)</label>
                                        <div class="col-lg-7">
                                            <input type="file" class="form-control" name="requirements_file"  accept="application/pdf"  required/>
                                            <a href="#" class="btn btn-effect" data-toggle="modal" data-target="#requirements-modal"><i class="fa fa-search"></i> View Requirements</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="select_organization_name" class="col-lg-4 control-label"><strong>Organization Name</strong></label>

                                        <div class="col-lg-7">
                                            <select name="organization_id" class="form-control"  id="org-id">
                                                <option value="">-- Select --</option>
                                                <%
                                                    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                                                    ArrayList<Organization> orgs = objO.getAllOrganizations();
                                                    for (Organization org : orgs) {
                                                %>
                                                <option value="<%=org.getId()%>"><%= org.getName()%></option>
                                                <%
                                                    }
                                                %>
                                                <option value="0">Other</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="organization_name" class="control-label col-lg-4"><strong>Organization Name</strong></label>
                                            <div class="col-lg-7">
                                                <input type="text" class="form-control" name="organization_name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="organization_address" class="control-label col-lg-4"><strong>Organization Address</strong></label>
                                            <div class="col-lg-7">
                                                <input type="text" class="form-control" name="organization_address">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="about_organization" class="control-label col-lg-4"><strong>About Organization</strong></label>
                                            <div class="col-lg-7">
                                                <input type="text" class="form-control" name="about_organization">
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
        <hr>
        <footer>
            <div class="container">
                <p>&copy; 2014 Electio</p>
            </div>
        </footer>
        <hr>


        <div class="modal" id="requirements-modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Nominee Requirements</h4>
                    </div>
                    <div class="modal-body">
                        <p><strong>A Nominee should have the following eligibilities -</strong></p>
                        <p>
                            <%
                                String requirements = DBDAOImplElection.getInstance().getElectionRequirements(election_id);
                            %>
                            <%=requirements%>
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal" id="nominee-modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">User Data exists</h4>
                    </div>
                    <div class="modal-body">
                        <p>Hello, the data for this email address is already exists in our database with the following details<br><b>Name : <span id="nominee-status"></span></b>
                            <br>
                            Would you like to skip <i>registration</i> and proceed to <b>Login</b>?
                        </p>

                    </div>
                    <div class="modal-footer">
                        <a href="#" class="btn btn-default" id="pass-link">Forgot Password?</a>
                        <a href="#" class="btn btn-primary" id="continue_link">Log In</a>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
