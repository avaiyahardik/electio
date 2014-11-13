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
                        <a href="#" class="btn btn-primary"><i class="fa fa-group"></i> Voters</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=probable_nominees" class="btn btn-info"><i class="fa fa-list"></i> Eligible Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=statistics" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-4">
                            <button class="btn btn-primary btn-sm btn-add" value="<%=el.getId()%>*addVoter"><i class="fa fa-plus"></i> Add Voter</button>
                            <a href="#" id="link-voter-upload">Upload CSV file</a>
                        </div>

                        <div id="upload-voters" style="display: none" class="col-md-6">
                            <form class="form-inline" action="UploadVoterFile" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="election_id" value="<%= el.getId()%>">
                                <div class="form-group">
                                    <input class="form-control" type="file" name="voter_file" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" required>
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
                            <div class="align-left col-lg-6">
                                <strong>Voter Status</strong>
                                <i class="fa fa-check-circle" style="color:green"></i> Voted
                                <i class="fa fa-circle" style="color:red"></i> Not Voted
                                <br>
                                <strong>Link Status</strong>
                                <i class="fa fa-check-circle" style="color:green"></i> Sent
                                <i class="fa fa-circle" style="color:red"></i> Not Sent
                            </div>

                            <div class="align-right col-lg-6">
                                <% String ballot_link = DOMAIN_BASE + "voter/login.jsp?election_id=" + el.getId();%>
                                <p> Ballot URL : <i><%=ballot_link%></i></p>
                                <a href="Controller?action=send_ballot_link&election_id=<%= el.getId()%>" class="btn btn-primary align-right btn-sm"><i class="fa fa-envelope"></i> Send Ballot URL</a>
                            </div>
                        </div>
                    </div>

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Voter Email</th>
                                <th class="align-center">Link Status</th>
                                <th class="align-center">Vote Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr id="blank_row_voter"></tr>
                            <!-- Display Voter Data by Loop -->
                            <% ArrayList<Voter> voters = (ArrayList<Voter>) request.getAttribute("voters");
                                for (Voter v : voters) {
                            %>
                            <tr>
                                <td><%= v.getEmail()%></td>

                                <td class="align-center"><%
                                    if (v.getLinkStatus()) {
                                    %>
                                    <i class="fa fa-check-circle" style="color:green"></i>
                                    <%
                                    } else {
                                    %>
                                    <i class="fa fa-circle" style="color:red"></i>
                                    <%
                                        }
                                    %>
                                </td>

                                <td class="align-center"><%
                                    if (v.getStatus()) {
                                    %>
                                    <i class="fa fa-check-circle" style="color:green"></i>
                                    <%
                                    } else {
                                    %>
                                    <i class="fa fa-circle" style="color:red"></i>
                                    <%
                                        }
                                    %></td>
                                <td>
                                    <button value="<%= v.getElection_id()%>*<%= v.getEmail()%>*editVoter" class="btn-edit btn-default btn-sm"><i class="fa fa-edit"></i> Edit</button>
                                    <button value="<%= v.getElection_id()%>*<%= v.getEmail()%>*deleteVoter" class="btn-del btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i> Delete</button>
                                </td>
                            </tr>
                            <%}%>
                            <%
                                if (voters.size() == 0) {
                            %>
                            <tr id="no-voter-row">
                                <td colspan="4"><br><strong>Your voters will be displayed here...</strong></td>
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
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
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