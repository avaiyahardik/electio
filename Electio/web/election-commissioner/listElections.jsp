<%@page import="java.sql.Timestamp"%>
<%@page import="DAO.DBDAOImplElection"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.ElectionType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<link href="../assets/plugins/modal/css/component.css" rel="stylesheet">
<%

    /*
     if (new Timestamp(date.getTime()).compareTo(e.getNomination_start()) >= 0 && new Timestamp(date.getTime()).compareTo(e.getNomination_start()) <= 0) {
     status=1;
     } else if (new Timestamp(date.getTime()).compareTo(e.getWithdrawal_start()) >= 0 && new Timestamp(date.getTime()).compareTo(e.getWithdrawal_end()) <= 0) {
     status=2;
     } else if (new Timestamp(date.getTime()).compareTo(e.getVoting_start()) >= 0 && new Timestamp(date.getTime()).compareTo(e.getVoting_end()) <= 0) {
     status=3;
     } else if (new Timestamp(date.getTime()).compareTo(e.getVoting_end()) >= 0) {
     status=4;
     }
     }
     */%>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title"><strong>Election List</strong></h3>
        </div>

        <div class="panel-body">
            <!-- BEGIN ERROR BOX --> 
            <%
                String err0 = (String) request.getAttribute("err");
                if (err0 != null) {
            %>
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <%=err0%>

            </div>
            <% }%>
            <!-- END ERROR BOX --> 

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 table-responsive table-red">
                    <table class="table table-hover table-dynamic table-tools">
                        <thead>
                            <tr>
                                <th>Election Name</th>
                                <th>Created on</th>
                                <th>Election Type</th>
                                <th>Status</th>
                                <th>Actions</th>
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
                                int election_status = 0;
                                Date date = new Date();
                                Timestamp temp = new Timestamp(date.getTime());
                                for (Election el : elections) {
//                                    DBDAOImplementation obj = DBDAOImplementation.getInstance();

                                    if (temp.after(el.getNomination_start()) && temp.before(el.getNomination_end())) {
                                        election_status = 1;
                                    } else if (temp.after(el.getWithdrawal_start()) && temp.before(el.getWithdrawal_end())) {
                                        election_status = 2;
                                    } else if (temp.after(el.getVoting_start()) && temp.before(el.getVoting_end())) {
                                        election_status = 3;
                                    } else if (temp.after(el.getVoting_end())) {
                                        election_status = 4;
                                    } else {
                                        election_status = 0; // awaiting
                                    }
                                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                                    ElectionType et = objE.getElectionType(el.getType_id());

                            %>

                            <tr>
                                <td><strong><a href="Controller?action=view_election_detail&id=<%= el.getId()%>"><%= el.getName()%></a></strong></td>
                                <td><%=sdf.format(new Date(el.getCreated_at().getTime()))%> </td>
                                <td><%= et.getType()%></td>
                                
                                <td><% switch(election_status){
                                    case 0: %> <label class="label label-primary">Due</label>
                                    <%break; case 1:%><label class="label label-warning">Nomination Period</label>
                                    <%break;case 2:%><label class="label label-danger">Withdrawal Period</label>
                                    <%break;case 3:%><label class="label label-info">Voting</label>
                                    <%break;case 4:%><label class="label label-success">Finished</label>
                                <%}%>
                                    
                                </td>
                                <td>
                                    <a href="Controller?action=download_election_data&id=<%= el.getId()%>" class="btn btn-default btn-sm"><i class="fa fa-download"></i> Download Data</a>
                                    <a href="Controller?action=delete_election&id=<%= el.getId()%>" class="btn btn-effect btn-danger btn-sm"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                </td>
                            </tr>
                            <%}%>
                            <%
                                if (elections.size() == 0) {
                            %>
                            <tr>
                                <td colspan="4"><strong>Your elections will be displayed here...</strong></td>
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

<jsp:include page="footer.jsp"/>