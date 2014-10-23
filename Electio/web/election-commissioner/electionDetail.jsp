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
<link rel="stylesheet" href="../assets/plugins/datatables/dataTables.css">
<link rel="stylesheet" href="../assets/plugins/datatables/dataTables.tableTools.css">
<link rel="stylesheet" type="text/css" href="../assets/dtp/jquery.datetimepicker.css"/>

<!-- END PAGE LEVEL STYLE -->
<% Election el = (Election) request.getAttribute("election");
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
            <div class="tabcordian">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#election_general" data-toggle="tab">General</a></li>
                    <li><a href="#election_nominees" data-toggle="tab">Nominees</a></li>
                    <li><a href="#election_candidates" data-toggle="tab">Candidates</a></li>
                    <li><a href="#election_voters" data-toggle="tab">Voters</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content">

                    <div class="tab-pane fade active in" id="election_general">

                        <form action="Controller" method="POST" class="form-horizontal">
                            <fieldset>

                                <div class="col-lg-10">
                                    <div class="form-group">
                                        <div class="col-lg-8 col-lg-offset-2">
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
                                    </div>

                                    <input type="hidden" name="id" value="<%= el.getId()%>">
                                    <div class="form-group">
                                        <label for="name" class="control-label col-lg-3"><strong>Election Name</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="name" value="<%=el.getName()%>">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="description" class="control-label col-lg-3"><strong>Description</strong></label>
                                        <div class="col-lg-7">
                                            <textarea class="form-control" name="description">
                                                <%= el.getDescription()%>
                                            </textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="requirements" class="control-label col-lg-3"><strong>Eligibility Requirements</strong></label>
                                        <div class="col-lg-7">
                                            <div class="tinymceScreen">
                                                <textarea name="requirements" id="requirements">
                                                    <%= el.getRequirements()%>
                                                </textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="type" class="control-label col-lg-3"><strong>Election Type</strong></label>
                                        <div class="col-lg-7">
                                            <select class="form-control" name="type">
                                                <% if (el.getType_id() == 2) {%>
                                                <option value="2" selected="selected">Weighted</option>
                                                <option value="1">Preferential</option>
                                                <%} else {%>
                                                <option value="2">Weighted</option>
                                                <option value="1" selected="selected">Preferential</option>
                                                <%}%>                                                    
                                                }
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="nomination_start" class="control-label col-lg-3"><strong>Nomination Starts on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="nomination_start" id="nomination_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getNomination_start().getTime()))%>"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="nomination_end" class="control-label col-lg-3"><strong>Nomination Ends on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="nomination_end" id="nomination_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getNomination_end().getTime()))%>"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="withdrawal_start" class="control-label col-lg-3"><strong>Withdrawal Starts on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="withdrawal_start" id="withdrawal_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getWithdrawal_start().getTime()))%>"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="withdrawal_end" class="control-label col-lg-3"><strong>Withdrawal Ends on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="withdrawal_end" id="withdrawal_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getWithdrawal_end().getTime()))%>"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="voting_start" class="control-label col-lg-3"><strong>Voting Starts on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="voting_start" id="voting_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getVoting_start().getTime()))%>"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="voting_end" class="control-label col-lg-3"><strong>Voting Ends on</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="voting_end" id="voting_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getVoting_end().getTime()))%>"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="control-label col-lg-3"><strong>Petition Period (In Days)</strong></label>
                                        <div class="col-lg-7">
                                            <input type="text" class="form-control" name="petition_duration" value="<%= el.getPetition_duration()%>">
                                        </div>
                                    </div>
                                    <br>

                                    <div class="form-group">
                                        <div class="col-lg-7 col-lg-offset-3">
                                            <button type="submit" name="action" value="update_election" class="btn btn-success"><i class="fa fa-floppy-o"></i> Save</button>
                                            <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Clear</button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                    <div class="tab-pane fade active in" id="election_nominees">
                        <h4>Nominees' list for the election</h4>
                        <table class="table table-hover table-dynamic table-tools">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Mobile</th>
                                    <th>Image</th>
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
                                        <a href="#">
                                            <img src="<%= n.getImage()%>" height="75" width="60" alt="Nominee Photo"/>
                                        </a>
                                    </td>
                                </tr>
                                <%}%>
                                <%
                                    if (nominees.size() == 0) {
                                %>
                                <tr>
                                    <td colspan="4"><strong>Nominees will be displayed here...</strong></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                    </div>

                    <div class="tab-pane fade active in" id="election_candidates">
                        <h4>Candidates' List for the election</h4>
                        <table class="table table-hover table-dynamic table-tools">
                            <thead>
                                <tr>
                                    <th>Candidate Name</th>
                                    <th>Email ID</th>
                                    <th>Mobile No</th>
                                    <th>Photo</th>
                                    <th>Action</th>
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
                                        <a href="#">
                                            <img src="<%= c.getImage()%>" height="75" width="60" alt="Candidate Photo"/>
                                        </a>
                                    </td>   
                                    <td>
                                        <a href="Controller?action=delete_candidate&election_id=<%= c.getElection_id()%>&email=<%= c.getEmail()%>" class="btn btn-effect btn-danger btn-sm"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>

                                </tr>
                                <%}%>
                                <%
                                    if (candidates.size() == 0) {
                                %>
                                <tr>
                                    <td colspan="5"><strong>Candidates will be displayed here...</strong></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>

                    </div>

                    <div class="tab-pane fade active in" id="election_voters">
                        <h4>Voters' List for the election</h4>
                        <table class="table table-hover table-dynamic table-tools">
                            <thead>
                                <tr>
                                    <th>Voter Email</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <% ArrayList<Voter> voters = (ArrayList<Voter>) request.getAttribute("voters");
                                    for (Voter v : voters) {
                                %>

                                <!-- Display Voter Data by Loop -->
                                <tr>
                                    <td><%= v.getEmail()%></td>
                                    <td><%= v.getStatus()%></td>
                                    <td>
                                        <a href="Controller?action=edit_voter&election_id=<%= v.getElection_id()%>&email=<%= v.getEmail()%>" class="btn btn-default btn-sm"><i class="fa fa-download"></i> Update</a>
                                        <a href="Controller?action=delete_voter&election_id=<%= v.getElection_id()%>&email=<%= v.getEmail()%>" class="btn btn-effect btn-danger btn-sm"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                                <%}%>
                                <%
                                    if (voters.size() == 0) {
                                %>
                                <tr>
                                    <td colspan="3"><strong>Voters will be displayed here...</strong></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>




<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/plugins/magnific/jquery.magnific-popup.min.js"></script>
<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.datetimepicker.js"></script>
<!-- END  PAGE LEVEL SCRIPTS -->


<script type="text/javascript">

    $('#nomination_start').datetimepicker()
            .datetimepicker({step: 30});

    $('#nomination_end').datetimepicker()
            .datetimepicker({step: 30});

    $('#withdrawal_start').datetimepicker()
            .datetimepicker({step: 30});
    $('#withdrawal_end').datetimepicker()
            .datetimepicker({step: 30});

    $('#voting_start').datetimepicker()
            .datetimepicker({step: 30});

    $('#voting_end').datetimepicker()
            .datetimepicker({step: 30});

    tinymce.init({selector: '#requirements'});

</script>
<jsp:include page="footer.jsp"/>