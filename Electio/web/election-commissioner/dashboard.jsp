<%@page import="DAO.DBDAOImplElection"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.ElectionType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<link href="../assets/plugins/modal/css/component.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../assets/dtp/jquery.datetimepicker.css"/>

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title"><strong>Recently Finished Elections</strong></h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 table-responsive table-red">
                    <table class="table table-hover table-dynamic table-tools">
                        <thead>
                            <tr>
                                <th>Election Name</th>
                                <th>Created At</th>
                                <th>Election Type</th>
                                <th>Voting Ended</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--
                            Show the data using loop here
                            Example set is given here
                            -->
                            <%
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                                ArrayList<Election> elections = (ArrayList<Election>) request.getAttribute("elections");
                                for (Election el : elections) {
//                                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                                    ElectionType et = objE.getElectionType(el.getType_id());
                            %>

                            <tr>
                                <td><strong><a href="Controller?action=view_election_detail&id=<%= el.getId()%>&tab=general"><%= el.getName()%></a></strong></strong></td>
                                <td><%= sdf.format(new Date(el.getCreated_at().getTime()))%></td>
                                <td><%= et.getType()%></td>
                                <td><%=sdf.format(new Date(el.getVoting_end().getTime()))%> </td>
                                <td><a href="Controller?action=election_result&election_id=<%= el.getId()%>" class="btn btn-info btn-sm"><i class="fa fa-bar-chart-o"></i> View Results</a>
                                    <button value="<%= el.getId()%>" class="btn btn-warning btn-sm btn-effect btn-reelection" data-toggle="modal" data-target="#restart-modal"><i class="fa fa-rotate-left"></i> Re-Election</button>
                                </td>

                            </tr>
                            <%}%>
                            <%
                                if (elections.size() == 0) {
                            %>
                            <tr>
                                <td colspan="5"><br><strong>Your recently finished elections will be displayed here...</strong></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="../assets/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<script src="../assets/plugins/modal/js/classie.js"></script>
<script src="../assets/plugins/modal/js/modalEffects.js"></script>
<script type="text/javascript">
    function confirmReelection() {
        if (confirm('Are you sure want to do re-election?')) {
            return true;
        } else {
            return false;
        }
    }
</script>

<div class="modal" id="restart-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Announce Re-Election</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="Controller" method="POST">
                    <input type="hidden" name="election_id" id="ele-id" value="">
                    <div class="form-group col-md-12">
                        <label for="new_start_date" class="control-label col-md-4">Voting Start Date</label>
                        <div class="col-md-8">
                            <input type="text" required class="form-control" name="voting_start" id="voting_start" placeholder="Click to choose date"/>
                        </div>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="new_end_date" class="control-label col-md-4">Voting End Date</label>
                        <div class="col-md-8">
                            <input type="text" required class="form-control" name="voting_end" id="voting_end" placeholder="Click to choose date"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class=" col-md-12 col-md-offset-4">
                            <button type="submit" class="btn btn-primary" name="action" value="restart_election">Announce Re-Election</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.datetimepicker.js"></script>
<script type="text/javascript">
    $('#voting_start').datetimepicker()
            .datetimepicker({step: 30});

    $('#voting_end').datetimepicker()
            .datetimepicker({step: 30});

$('.btn-reelection').click(function(){
    var election_id = $(this).val();
    $('#ele-id').val(election_id);
});
</script>

<jsp:include page="footer.jsp"/>