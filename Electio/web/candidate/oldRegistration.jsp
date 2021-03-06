<%-- 
    Document   : nomineeRegistration
    Created on : Oct 22, 2014, 2:15:32 PM
    Author     : Vishal Jain
--%>

<%@page import="DAO.DBDAOImplElection"%>
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
                                    long election_id = 0;
                                    String email = "";
                                    if (request.getAttribute("election_id") != null) {
                                        election_id = Long.parseLong((String) request.getAttribute("election_id"));
                                        name = (String) request.getAttribute("name");
                                        email = (String) request.getAttribute("email");
                                    }%>
                                <strong><%= name%></strong>
                            </span>
                        </div>

                        <div class="panel-body">
                            <form action="RegisterExistingNominee" method="POST" class="form-horizontal" enctype="multipart/form-data">
                                <input type="hidden" name="election_id" value="<%= election_id%>">
                                <input type="hidden" name="email" value="<%=email%>">
                                <fieldset> 
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


                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="firstname"><strong>Name</strong></label>
                                        <div class="col-sm-7">
                                            <label class="form-control"><%=name%></label>
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
                                        <label for="password" class="control-label col-lg-4"><strong>Password</strong></label>
                                        <div class="col-lg-7">
                                            <input type="password" class="form-control" name="password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-lg-7 col-lg-offset-4">
                                            <button type="submit" name="cmd" value="save" class="btn btn-primary"><i class="fa fa-floppy-o"></i> File Nomination</button>
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

        <script type="text/javascript">
            document.getElementById('requirements_file').addEventListener('change', checkFile, false);
            function checkFile(e) {
                var file_list = e.target.files;
                for (var i = 0, file; file = file_list[i]; i++) {
                    var sFileName = file.name;
                    var sFileExtension = sFileName.split('.')[sFileName.split('.').length - 1].toLowerCase();
                    var iFileSize = file.size;
                    var iConvert = (file.size / 1024000).toFixed(2);

                    if (!(sFileExtension === "pdf") || iFileSize > 102400) {
                        // txt = "File type : " + sFileExtension + "\n\n";
                        //txt += "Size: " + iConvert + " MB \n\n";
                        txt = "Please make sure your file is in pdf or doc format and less than or equal 100 KB.\n\n";
                        alert(txt);
                    }
                }
            }
        </script>
    </body>
</html>
