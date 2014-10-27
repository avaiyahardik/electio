<%@page import="Model.Organization"%>
<%@page import="Model.Nominee"%>
<jsp:include page="headerSidebar.jsp"/>
<div id="main-content">
    <div class="page-title">
        <h2>Nominee Details</h2>
    </div>
    <div class="row">
        <div class="col-md-12">            
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="Controller" method="GET" class="form-horizontal">

                        <%
                            Nominee n = (Nominee) request.getAttribute("nominee");
                            Organization organization = (Organization) request.getAttribute("organization");
                        %>
                        <div class="col-md-3">
                            <img src="<%= n.getImage()%>" alt="Nominee Image"/>
                        </div>

                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-12 profile-info">
                                    <h1>
                                        <%=n.getFirstname()%> <%= n.getLastname()%>
                                            <% int status = n.getStatus();
                                                if (status == 0) {
                                            %>
                                            <label class="label label-warning" style="font-size:13px"><i class="fa fa-clock-o"></i> Waiting</label>
                                            <%
                                            } else if (status == 1) {
                                            %>
                                            <label class="label label-success" style="font-size:13px"><i class="glyphicon glyphicon-check"></i> Approved</label>
                                            <%} else {
                                            %>
                                            <label class="label label-danger" style="font-size:13px"><i class="glyphicon glyphicon-remove"></i> Rejected</label>
                                            <%
                                                }
                                            %>
                                    </h1>
                                    <ul class="list-unstyled list-inline">
                                        <li class="m-r-20"><i class="p-r-5 c-dark">@</i> <%= n.getEmail()%></li>
                                        <li class="m-r-20"><i class="fa fa-mobile p-r-5 c-purple"></i> <%= n.getMobile()%></li>
                                        <li class="m-r-20"><i class="fa fa-info p-r-5 c-brown"></i> <%= organization.getName()%></li>
                                        <li class="m-r-20"><i class="fa fa-envelope p-r-5 c-blue"></i><%= organization.getAddress()%> </li>
                                        <li><i class="fa fa-tag p-r-5 c-red"></i> <%= organization.getAbout()%></li>
                                    </ul>
                                    <div class="m-t-20">
                                    </div>
                                    <a href="<%= n.getRequirements_file()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements</a>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="align-center">
                                    <br>
                                    <br>
                                    <a href="Controller?action=approve_nominee&election_id=<%= n.getElection_id()%>&email=<%= n.getEmail()%>&requirements_file=<%= n.getRequirements_file()%>" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                                    <a href="Controller?action=reject_nominee&election_id=<%= n.getElection_id()%>&email=<%= n.getEmail()%>" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Reject</a>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>