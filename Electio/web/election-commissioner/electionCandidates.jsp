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
                        <a href="#" class="btn btn-primary"><i class="fa fa-user"></i> Candidates</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=voters" class="btn btn-info"><i class="fa fa-group"></i> Voters</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=probable_nominees" class="btn btn-info"><i class="fa fa-list"></i> Eligible Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=statistics" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>`

                <div class="panel-body">
                    <table class="table table-hover table-dynamic table-tools">
                        <thead>
                            <tr>
                                <th>Candidate Name</th>
                                <th>Email ID</th>
                                <th>Mobile No</th>
                                <th>Photo</th>
                                <!-- <th>Action</th> -->
                            </tr>
                        </thead>

                        <tbody>

                            <!-- Display Candidate Data by Loop -->
                            <% ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
                                for (Candidate c : candidates) {
                            %>
                            <tr>
                                <td>
                                    <a href="Controller?action=candidate_details&election_id=<%= c.getElection_id()%>&email=<%= c.getEmail()%>"><strong><%= c.getFirstname()%> <%= c.getLastname()%></strong></a>
                                </td>
                                <td><%= c.getEmail()%></td>
                                <td><%= c.getMobile()%></td>
                                <td>
                                    <a href="#" data-toggle="modal" data-target="#image-modal" class="img-link">
                                        <img src="..<%= File.separator%><%= c.getImage()%>" height="75" width="60" alt="Candidate Photo"/>
                                    </a>
                                </td> 
                                <!--
                                <td>
                                    <a href="Controller?action=delete_candidate&election_id=<%= c.getElection_id()%>&email=<%= c.getEmail()%>&reason=no reason" class="btn btn-effect btn-danger btn-sm"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                </td
                                -->
                            </tr>
                            <%}%>
                            <%
                                if (candidates.size() == 0) {
                            %>
                            <tr>
                                <td colspan="5"><br><strong>Candidates will be displayed here...</strong></td>
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

<div class="modal" id="image-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Photo</h4>
            </div>
            <div class="modal-body align-center">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>