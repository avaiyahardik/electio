<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page import="DAO.DBDAOImplCandidate"%>
<%@page import="Model.Organization"%>
<%@page import="Model.Candidate"%>
<%@page import="java.io.File"%>
<%@page import="Model.Nominee"%>
<jsp:include page="header.jsp"/>

<%
    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
    Candidate c = (Candidate) request.getAttribute("candidate");
    Organization o = objO.getOrganization(c.getElection_id());
%>
<div class="page-header">
    <h1 class="page-title">Candidate Profile
    </h1>
</div>
<div class="row well">
    <div class="col-lg-9">
        <div class="panel panel-info">
            <div class="panel-heading">
                <p>Candidate Profile</p>
            </div>
            <div class="panel-body">
                <form action="Controller" method="POST" class="form-horizontal" enctype="multipart/form-data">
                    <fieldset> 
                        <!-- BEGIN ERROR BOX -->
                        <div class="form-group col-lg-12">
                            <% String msg = (String) request.getAttribute("msg");
                                if (msg != null) {%>

                            <div class="alert alert-info">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=msg%>
                            </div>
                            <% }%>
                            <% String err = (String) request.getAttribute("err");
                                if (err != null) {%>
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=err%>
                            </div>
                            <% }%>
                        </div>
                        <!-- END ERROR BOX -->  

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="firstname">First Name</label>
                            <div class="col-sm-6">
                                <input type="text" disabled="disabled" name="firstname" class="form-control" required value="<%=c.getFirstname()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="lastname">Last Name</label>
                            <div class="col-sm-6">
                                <input type="text"  disabled="disabled" name="lastname" class="form-control" required value="<%=c.getLastname()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-lg-4">Email ID</label>
                            <div class="col-lg-6">
                                <input type="text" disabled="disabled" name="email" class="form-control" required value="<%=c.getEmail()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="gender" class="control-label col-lg-4">Gender</label>
                            <div class="col-sm-6">
                                <% String gender = "Other";
                                    if (c.getGender() == 1) {
                                        gender = "Male";
                                    } else if (c.getGender() == 2) {
                                        gender = "Female";
                                    }
                                %>
                                <input type="text" disabled="disabled" name="gender" class="form-control" required value="<%=gender%>">
                            </div> 
                        </div>

                        <div class="form-group">
                            <label for="mobile" class="col-lg-4 control-label">Mobile No</label>
                            <div class="col-lg-6">
                                <input type="text" disabled="disabled" class="form-control" name="mobile" required value="<%=c.getMobile()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="organization_name" class="control-label col-lg-4">Organization Name</label>
                            <div class="col-lg-6">
                                <input type="text" disabled="disabled" class="form-control" name="organization_name" required value="<%=o.getName()%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organization_address" class="control-label col-lg-4">Organization Address</label>
                            <div class="col-lg-6">
                                <input type="text" disabled="disabled" class="form-control" name="organization_address" required value="<%=o.getAddress()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="about_organization" class="control-label col-lg-4">About Organization</label>
                            <div class="col-lg-6">
                                <input type="text"  disabled="disabled" class="form-control" name="about_organization" required value="<%=o.getAbout()%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="manifesto" class="control-label col-lg-4">Manifesto</label>
                            <div class="col-lg-6">
                                <a href="..<%= File.separator%><%= c.getManifesto()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Manifesto</a>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>

    <div class="col-lg-3">
        <div class="panel panel-info">
            <div class="panel-heading"><p>Photo</p></div>
            <div class="panel-body align-center">
                <img src="..<%= File.separator%><%= c.getImage()%>" height="200" width="200" align="center" class="thumbnail"/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>