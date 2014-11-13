<%@page import="Utilities.RandomString"%>
<%@page import="Model.User"%>
<%@page import="DAO.DBDAOImplUser"%>
<%@page import="Model.EligibleNominee"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Candidate"%>
<%@page import="Model.Nominee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Voter"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<!-- BEGIN PAGE LEVEL STYLE -->
<link rel="stylesheet" href="../assets/plugins/magnific/magnific-popup.css">    
<!-- END PAGE LEVEL STYLE -->
<%
    String DOMAIN_BASE = request.getRequestURL().substring(0, request.getRequestURL().indexOf("Electio") + 8);
    Election el = (Election) request.getAttribute("election");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<div id="main-content">
    <div class="page-title">
        <h3><strong>
                <%= el.getName()%>
            </strong></h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading align-center">
                    <div class="btn-group">
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=general" class="btn btn-info"><i class="fa fa-info-circle"></i> General</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=nominees" class="btn btn-info"><i class="fa fa-user"></i> Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=candidates" class="btn btn-info"><i class="fa fa-user"></i> Candidates</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=voters" class="btn btn-info"><i class="fa fa-group"></i> Voters</a>
                        <a href="#" class="btn btn-primary"><i class="fa fa-list"></i> Probable Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=statistics" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-4">
                            <button class="btn btn-primary btn-sm btn-add" value="<%=el.getId()%>*addNominee"><i class="fa fa-plus"></i> Add New </button>
                            <a href="#" id="link-nominee-upload">Upload CSV file</a>
                        </div>

                        <div id="upload-nominees" style="display: none" class="col-md-6">
                            <form class="form-inline" action="UploadProbableNomineeFile" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="election_id" value="<%= el.getId()%>">
                                <div class="form-group">
                                    <input class="form-control" type="file" name="nominee_file" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" required>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-default btn-sm" name="action" value="import_voter" type="submit"><i class="fa fa-upload"></i> Upload</button>
                                    <a href="#" data-toggle="modal" data-target="#demo-file">File Format?</a>
                                </div>
                            </form>

                        </div>
                    </div>


                    <br><br>
                    <div class="well">
                        <div class="row">
                            <div class="col-lg-4">
                                <strong>Status</strong><br>
                                <i class="fa fa-circle" style="color:red"></i> Link Not Sent<br>
                                <i class="fa fa-circle" style="color:orange"></i> Link Sent<br>
                                <i class="fa fa-check-circle" style="color:green"></i> Registered
                            </div>

                            <div class="col-lg-8 align-right">
                                <% String reg_link = DOMAIN_BASE + "candidate/nomineeRegistration.jsp?election_id=" + el.getId();%>
                                <p> Nominee Registration URL : <i><%=reg_link%></i></p>
                                <a href="Controller?action=send_registration_link&election_id=<%= el.getId()%>" class="btn btn-primary align-right btn-sm"><i class="fa fa-envelope"></i> Send Registration URL</a>
                            </div>
                        </div>

                    </div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Nominee Email</th>
                                <th class="align-center">Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr id="blank_row_nominee"></tr>
                            <!-- Display Voter Data by Loop -->

                            <% ArrayList<EligibleNominee> pn = (ArrayList<EligibleNominee>) request.getAttribute("probable_nominee");
                                for (EligibleNominee p : pn) {
                            %>
                            <tr>
                                <td><%= p.getEmail()%></td>
                                <td class="align-center"><%
                                    int p_status = p.getStatus();
                                    if (p_status == 0) {
                                    %>
                                    <i class="fa fa-circle" style="color:red"></i>
                                    <%} else if (p_status == 1) {%>
                                    <i class="fa fa-circle" style="color:orange"></i>
                                    <%
                                    } else {
                                    %>
                                    <i class="fa fa-check-circle" style="color:green"></i>
                                    <%
                                        }
                                    %></td>
                                <td>
                                    <button value="<%= p.getElection_id()%>*<%= p.getEmail()%>*editNominee" class="btn-edit btn-default btn-sm"><i class="fa fa-edit"></i> Edit</button>
                                    <button value="<%= p.getElection_id()%>*<%= p.getEmail()%>*deleteNominee" class="btn-del btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i> Delete</button>
                                </td>
                            </tr>
                            <%}%>
                            <%if (pn.size() == 0) {%>
                            <tr id="no-nominee-row">
                                <td colspan="3"><br><strong>Your eligible nominees will be displayed here...</strong></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/plugins/magnific/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<!-- END  PAGE LEVEL SCRIPTS -->

<div class="modal" id="demo-file">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">The file should have data in the below format</h4>
            </div>
            <div class="modal-body">

                <p class="well align-left">
                    email1@domain.tld<br/>
                    email2@domain.tld<br/>
                    email3@domain.tld<br/>
                    email4@domain.tld<br/>
                    email5@domain.tld<br/>
                </p>

            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>