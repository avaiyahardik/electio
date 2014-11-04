<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.ElectionType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<link href="../assets/plugins/modal/css/component.css" rel="stylesheet">

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
                                <th>Election Type</th>
                                <th>Voting Ended</th>
                                <th></th>
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
                                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                                    ElectionType et = obj.getElectionType(el.getType_id());
                            %>

                            <tr>
                                <td><strong><a href="Controller?action=view_election_detail&id=<%= el.getId()%>"><%= el.getName()%></a></strong></strong></td>
                                <td><%= et.getType()%></td>
                                <td><%=sdf.format(new Date(el.getVoting_end().getTime()))%> </td>
                                <td><a href="Controller?action=election_result&id=<%= el.getId()%>" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> View Results</a></td>
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