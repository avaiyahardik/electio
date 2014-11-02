<%@page import="java.io.File"%>
<%@page import="Model.Organization"%>
<%@page import="Model.Candidate"%>
<jsp:include page="headerSidebar.jsp"/>
<div id="main-content">
    <div class="page-title">
        <h2>Candidate Details</h2>
    </div>
    <%
        Candidate c = (Candidate) request.getAttribute("candidate");
        Organization organization = (Organization) request.getAttribute("organization");
    %>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-3">
                        <img src="..<%= File.separator%><%= c.getImage()%>" alt="Candidate Image"/>
                        <!-- replace the Image source -->
                    </div>

                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-12 profile-info">
                                <h1>

                                    <!-- Remove the Above name and put the name from DB -->
                                    <%= c.getFirstname()%> <%= c.getLastname()%>
                                </h1>

                                <div class="m-t-10"></div>
                                <ul class="list-unstyled list-inline">
                                    <li class="m-r-20"><i class="p-r-5 c-dark">@</i><%= c.getEmail()%></li>
                                    <li class="m-r-20"><i class="fa fa-mobile p-r-5 c-purple"></i> <%= c.getMobile()%></li>
                                    <li class="m-r-20"><i class="fa fa-info p-r-5 c-brown"></i> <%= organization.getName()%></li>
                                    <li class="m-r-20"><i class="fa fa-envelope p-r-5 c-blue"></i> <%= organization.getAddress()%></li>
                                    <li><i class="fa fa-tag p-r-5 c-red"></i> <%= organization.getAddress()%></li>
                                </ul>
                                <div class="m-t-20"></div>
                                <a href="..<%= File.separator%><%= c.getRequirements_file()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements file</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="align-center">
                            <a href="Controller?action=approve_nominee&election_id=&email=" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                            <a href="Controller?action=reject_nominee&election_id=&email=" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Reject</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>