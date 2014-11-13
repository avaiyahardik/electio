<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="Model.Candidate"%>
<%@page import="Model.Organization"%>
<%@page import="java.io.File"%>
<%@page import="Model.Nominee"%>
<jsp:include page="header.jsp"/>

<%
    Nominee n = (Nominee) request.getAttribute("nominee");
    Candidate c = (Candidate) request.getAttribute("candidate");
    int status = n.getStatus();
    Organization o = (Organization) request.getAttribute("organization");
    String reason = (String) request.getAttribute("reason");
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
                <form action="UpdateCandidate" method="POST" class="form-horizontal" enctype="multipart/form-data">
                    <fieldset> 
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="status">Nomination Status</label>
                            <div class="col-sm-6">
                                <%if (status == 0) {%>
                                <label class="label label-info form-control" style="font-size:13px"><i class="fa fa-clock-o"></i> Waiting</label>
                                <%} else if (status == 1) {%>
                                <label class="label label-success control-label" style="font-size:13px"><i class="fa fa-check"></i> Approved</label>
                                <%} else if (status == 2) {%>
                                <label class="label label-danger form-control" style="font-size:13px"><i class="fa fa-times"></i> Rejected (<%= reason%>)</label>
                                <%} else if (status == 3) {%>
                                <label class="label label-warning form-control" style="font-size:13px"><i class="glyphicon glyphicon-thumbs-down"></i> Withdrawn</label>
                                <%}%>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="firstname">First Name</label>
                            <div class="col-sm-6">
                                <input type="text" name="firstname" class="form-control" required value="<%=n.getFirstname()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="lastname">Last Name</label>
                            <div class="col-sm-6">
                                <input type="text" name="lastname" class="form-control" required value="<%=n.getLastname()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-lg-4">Email ID</label>
                            <div class="col-lg-6">
                                <input type="email" name="email" class="form-control" required disabled="" value="<%=n.getEmail()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="gender" class="control-label col-lg-4">Gender</label>
                            <div class="col-sm-6">
                                <select name="gender" class="form-control">
                                    <% if (n.getGender() == 0) {%>
                                    <option value="0" selected="">Male</option>
                                    <option value="1">Female</option>
                                    <option value="2">Other</option>
                                    <%} else if (n.getGender() == 1) {%>
                                    <option value="0">Male</option>
                                    <option value="1" selected="">Female</option>
                                    <option value="2">Other</option>
                                    <%} else {%>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                    <option value="2" selected="">Other</option>
                                    <%}%>
                                </select>
                            </div> 
                        </div>

                        <div class="form-group">
                            <label for="mobile" class="col-lg-4 control-label">Mobile No</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="mobile" required value="<%=n.getMobile()%>"  pattern="[7-9]{1}[0-9]{9}" title="10 Digit mobile number">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="photo" class="control-label col-lg-4">Photo</label>
                            <div class="col-lg-6">
                                <input type="file" class="form-control" name="photo" accept="image/gif, image/jpeg, image/png"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="requirements_file" class="control-label col-lg-4"></label>
                            <div class="col-lg-6">
                                <a href="..<%= File.separator%><%= n.getRequirements_file()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements file</a>
                            </div>
                        </div>

                        <% if (n.getStatus() == 1) {%>
                        <div class="form-group">
                            <label for="manifesto" class="control-label col-lg-4">Manifesto(PDF only)</label>
                            <div class="col-lg-6">
                                <input type="file" class="form-control" name="manifesto_file"  accept="application/pdf" />
                                <a href="..<%= File.separator%><%= c.getManifesto()%>" class="btn btn-dark"><i class="fa fa-paperclip"></i> Current Manifesto file</a>
                            </div>
                        </div>
                        <%}%>
                        <div class="form-group">
                            <label for="select_organization_name" class="col-lg-4 control-label"><strong>Organization</strong></label>
                            <div class="col-lg-6">
                                <select name="organization_id" class="form-control"  id="org-id" onChange="checkOrg(this.value)">
                                    <option value="-1">-- Select --</option>
                                    <%
                                        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                                        ArrayList<Organization> orgs = objO.getAllOrganizations();
                                        for (Organization or : orgs) {
                                            if (or.getId() == n.getOrganization_id()) {
                                    %>
                                    <option selected="selected" value="<%=or.getId()%>"><%= or.getName()%></option>
                                    <%
                                    } else {
                                    %>
                                    <option value="<%=or.getId()%>"><%= or.getName()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                    <option value="0">Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="org-name">
                            <label for="organization_name" class="control-label col-lg-4">Organization Name</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="organization_name" value="<%=o.getName()%>">
                            </div>
                        </div>
                        <div class="form-group" id="org-address">
                            <label for="organization_address" class="control-label col-lg-4">Organization Address</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="organization_address" value="<%=o.getAddress()%>">
                            </div>
                        </div>

                        <div class="form-group" id="org-about">
                            <label for="about_organization" class="control-label col-lg-4">About Organization</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="about_organization" value="<%=o.getAbout()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-4">
                                <button type="submit" name="action" value="UpdateCandidate" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Update Profile</button>
                                <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Reset</button>
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
                <img src="..<%= File.separator%><%= n.getImage()%>" height="200" width="200" align="center" class="thumbnail"/>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading"><p>Change Password</p></div>

            <div class="panel-body">
                <form action="Controller" method="POST" class="form-inline">

                    <div class="form-group">
                        <label for="old_password" class="control-label col-lg-12" >Old Password</label>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="password" class="form-control" name="old_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_password" class="control-label col-lg-12">New Password</label>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="password" id="pass1" class="form-control" name="new_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="retype_new_password" class="control-label col-lg-12">Retype New Password</label>
                        <div class="col-lg-12">
                            <input type="password" id="pass2" class="form-control" name="retype_new_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters" onkeyup="checkPass(); return false;">
                             <span id="msg" class="label label-danger"></span>
                        </div>
                       
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-12"></label>
                        <div class="col-lg-12">
                            <button type="submit" name="action" value="update_nominee_password" class="btn btn-warning btn-sm"><i class="fa fa-floppy-o"></i> Change Password</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function checkOrg(val) {
        if (val == 0) {
            document.getElementById('org-name').style.display = "block";
            document.getElementById('org-address').style.display = "block";
            document.getElementById('org-about').style.display = "block";
        } else {
            document.getElementById('org-name').style.display = "none";
            document.getElementById('org-address').style.display = "none";
            document.getElementById('org-about').style.display = "none";
        }
    }

    function checkPass()
    {
        var pass1 = document.getElementById('pass1');
        var pass2 = document.getElementById('pass2');
        //Store the Confimation Message Object ...
        var message = document.getElementById('msg');

        if (pass1.value == pass2.value) {
            message.innerHTML = "Passwords Match!"
            message.classList.remove('label-danger');
            message.classList.add('label-success');
        } else {
            message.innerHTML = "Passwords Do Not Match!"
            message.classList.remove('label-success');
            message.classList.add('label-danger');
        }
    }
</script>
<jsp:include page="footer.jsp"/>