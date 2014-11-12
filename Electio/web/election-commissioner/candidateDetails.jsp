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
                    <div class="col-md-4">
                        <h1> </h1>
                        <img src="..<%= File.separator%><%= c.getImage()%>" alt="Candidate Image" width="250" height="300" class="thumbnail"/>
                        <!-- replace the Image source -->
                    </div>

                    <div class="col-md-7">
                        <div class="row">
                            <div class="col-md-12 profile-info">
                                <h1><%= c.getFirstname()%> <%= c.getLastname()%></h1>


                                <div class="form-group col-md-7">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-envelope p-r-5 c-blue"></i>
                                        </span>
                                        <label class="form-control"><%=c.getEmail()%></label>
                                    </div>    
                                </div>

                                <div class="form-group col-md-7">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-mobile p-r-5 c-purple"></i>
                                        </span>
                                        <label class="form-control"><%=c.getMobile()%></label>
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

                                <div class="form-group col-md-10">
                                    <a href="..<%= File.separator%><%= c.getRequirements_file()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements file</a>
                                    <a href="..<%= File.separator%><%= c.getManifesto()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Manifesto file</a>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!--
                                        <div class="col-md-12">
                                            <div class="align-center">
                                                <a href="Controller?action=delete_candidate&election_id=<%=c.getElection_id()%>&email=<%=c.getEmail()%>" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Delete Candidate</a>
                                            </div>
                                        </div>
                    -->
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>