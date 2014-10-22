<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.ElectionType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<link href="../assets/plugins/modal/css/component.css" rel="stylesheet">

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
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>

                            <!--
                            Show the data using loop here
                            Example set is given here
                            -->
                            <% ArrayList<Election> elections = (ArrayList<Election>) request.getAttribute("elections");
                                for (Election el : elections) {
                                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                                    ElectionType et = obj.getElectionType(el.getType_id());
                            %>

                            <tr>
                                <td><strong><a href="Controller?action=view_election_detail&id=<%= el.getId()%>"><%= el.getName()%></a></strong></td>
                                <td><%= el.getCreated_at()%> </td>
                                <td><%= et.getType()%></td>
                                <td>
                                    <a href="Controller?action=election_result&id=<%= el.getId()%>" class="btn btn-success btn-sm"><i class="fa fa-list"></i> Result</a>
                                    <a href="Controller?action=download_election_data&id=<%= el.getId()%>" class="btn btn-default btn-sm"><i class="fa fa-download"></i> Download Data</a>
                                    <a href="Controller?action=delete_election&id=<%= el.getId()%>" class="btn btn-effect btn-danger btn-sm"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                </td>
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