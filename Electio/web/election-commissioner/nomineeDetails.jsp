<%@page import="java.io.File"%>
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
                        <div class="col-md-5">
                            <img src="../<%= n.getImage()%>" alt="Nominee Image" width="200" height="300"/>
                        </div>

                        <div class="col-md-7">
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

                                    <div class="form-group col-md-7">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-envelope p-r-5 c-blue"></i>
                                            </span>
                                            <label class="form-control"><%=n.getEmail()%></label>
                                        </div>    
                                    </div>

                                    <div class="form-group col-md-7">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-mobile p-r-5 c-purple"></i>
                                            </span>
                                            <label class="form-control"><%=n.getMobile()%></label>
                                        </div>    
                                    </div>

                                    <div class="form-group col-md-7">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-info p-r-5 c-brown"></i>
                                            </span>
                                            <label class="form-control"><%=organization.getName()%></label>
                                        </div>    
                                    </div>

                                    <div class="form-group col-md-7">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-tag p-r-5 c-red"></i>
                                            </span>
                                            <label class="form-control"><%=organization.getAddress()%></label>
                                        </div>    
                                    </div>

                                    <div class="form-group col-md-7">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-tag p-r-5 c-red"></i>
                                            </span>
                                            <label class="form-control"><%=organization.getAbout()%></label>
                                        </div>    
                                    </div>
                                        
                                        
                                    <div class="form-group col-md-7">
                                        <a href="..<%= File.separator%><%= n.getRequirements_file()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements</a>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <% if (status == 0) {%>
                        <div class="col-md-12">
                            <div class="align-center">
                                <a href="Controller?action=nominee_action&cmd=approve&election_id=<%= n.getElection_id()%>&email=<%= n.getEmail()%>&requirements_file=<%= n.getRequirements_file()%>" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                                <a href="Controller?action=nominee_action&cmd=reject&election_id=<%= n.getElection_id()%>&email=<%= n.getEmail()%>" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Reject</a>
                            </div>
                        </div>
                        <%}%>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>