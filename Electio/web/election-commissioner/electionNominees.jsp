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
                        <a href="#" class="btn btn-primary"><i class="fa fa-user"></i> Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=candidates" class="btn btn-info"><i class="fa fa-user"></i> Candidates</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=voters" class="btn btn-info"><i class="fa fa-group"></i> Voters</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=probable_nominees" class="btn btn-info"><i class="fa fa-list"></i> Probable Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=statistics" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>

                <div class="panel-body">
                    <table class="table table-hover table-dynamic table-tools">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Mobile</th>
                                <th>Photo</th>
                                <th>Status</th>
                            </tr>
                        </thead>

                        <tbody>
                            <% ArrayList<Nominee> nominees = (ArrayList<Nominee>) request.getAttribute("nominees");
                                for (Nominee n : nominees) {
                            %>

                            <!-- Display Nominees Data by Loop -->
                            <tr>
                                <td>
                                    <a href="Controller?action=nominee_details&election_id=<%= n.getElection_id()%>&email=<%= n.getEmail()%>"><strong><%= n.getFirstname()%> <%= n.getLastname()%></strong></a>
                                </td>
                                <td><%= n.getEmail()%></td>
                                <td><%= n.getMobile()%></td>
                                <td>       
                                    <a href="#" data-toggle="modal" data-target="#image-modal" class="img-link">
                                        <img src="..<%= File.separator%><%= n.getImage()%>" height="75" width="60" alt="Nominee Photo"/>
                                    </a>
                                </td>

                                <td>
                                    <% int status = n.getStatus();
                                        if (status == 0) {
                                    %>
                                    <label class="label label-info" style="font-size:13px"><i class="fa fa-clock-o"></i> Waiting</label>
                                    <%
                                    } else if (status == 1) {
                                    %>
                                    <label class="label label-success" style="font-size:13px"><i class="glyphicon glyphicon-check"></i> Approved</label>
                                    <%} else if (status == 2) {
                                    %>
                                    <label class="label label-danger" style="font-size:13px"><i class="glyphicon glyphicon-remove"></i> Rejected</label>
                                    <%} else {
                                    %>
                                    <label class="label label-warning" style="font-size:13px"><i class="fa fa-thumbs-down"></i> Withdrawn</label>
                                    <%
                                        }
                                    %>
                                </td>

                            </tr>
                            <%}%>
                            <%
                                if (nominees.size() == 0) {
                            %>
                            <tr>
                                <td colspan="5"><br><strong>Nominees will be displayed here...</strong></td>
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