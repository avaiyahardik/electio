<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="Model.Organization"%>
<%@page import="Model.ElectionCommissioner"%>
<jsp:include page="headerSidebar.jsp"/>


<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title">Election Commissioner Profile</h3>
        </div>

        <div class="panel-body">
            <div class="col-lg-6">
                <form action="Controller" method="POST" class="form-horizontal">
                    <fieldset>
                        <%
                            ElectionCommissioner ec = (ElectionCommissioner) request.getAttribute("election_commissioner");
                            Organization organization = (Organization) request.getAttribute("organization");
                        %>
                        <div class="form-group">
                            <label class="control-label col-sm-5" for="firstname"><strong>First Name</strong></label>
                            <div class="col-sm-7">
                                <input type="text" name="firstname" class="form-control" required="required" value="<%=ec.getFirstname()%>" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5" for="lastname" ><strong>Last Name</strong></label>
                            <div class="col-sm-7">
                                <input type="text" name="lastname" class="form-control" required="required" value="<%=ec.getLastname()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-lg-5"><strong>Email ID</strong></label>
                            <div class="col-lg-7">
                                <input type="email" name="email" disabled="disabled" class="form-control" required value="<%=ec.getEmail()%>">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="mobile" class="col-lg-5 control-label"><strong>Mobile No</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i>+91</i> 
                                    </span>
                                    <input type="text" class="form-control" required="required" name="mobile"  value="<%=ec.getMobile()%>" pattern="[7-9]{1}[0-9]{9}" title="10 Digit mobile number">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="select_organization_name" class="col-lg-5 control-label"><strong>Organization</strong></label>
                            <div class="col-lg-7">
                                <select name="organization_id" class="form-control"  id="org-id" onChange="checkOrg(this.value)">
                                    <option value="-1">-- Select --</option>
                                    <%
                                        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                                        ArrayList<Organization> orgs = objO.getAllOrganizations();
                                        for (Organization o : orgs) {
                                            if (o.getId() == ec.getOrganization_id()) {
                                    %>
                                    <option selected="selected" value="<%=o.getId()%>"><%= o.getName()%></option>
                                    <%
                                    } else {
                                    %>
                                    <option value="<%=o.getId()%>"><%= o.getName()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                    <option value="0">Other</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" id="org-name">
                            <label for="organization_name" class="control-label col-lg-5"><strong>Organization Name</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" required="required" name="organization_name"  value="<%= organization.getName()%>">
                            </div>
                        </div>
                        <div class="form-group" id="org-address">
                            <label for="organization_address" class="control-label col-lg-5"><strong>Organization Address</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" required="required" name="organization_address" value="<%= organization.getAddress()%>">
                            </div>
                        </div>
                        <div class="form-group" id="org-about">
                            <label for="about_organization" class="control-label col-lg-5"><strong>About Organization</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" required="required" name="about_organization" value="<%= organization.getAbout()%>">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-5">
                                <button type="submit" name="action" value="update_election_commissioner_profile" class="btn btn-success"><i class="fa fa-floppy-o"></i> Update Profile</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>

            <div class="col-lg-6">
                <form action="Controller" method="POST" class="form-horizontal">
                    <fieldset>

                        <div class="form-group">
                            <label for="old_password" class="control-label col-lg-4"><strong>Old Password</strong></label>
                            <div class="col-lg-7">
                                <input type="password" class="form-control" name="old_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="new_password" class="control-label col-lg-4"><strong>Password</strong></label>
                            <div class="col-lg-7">
                                <input type="password" id="pass1" class="form-control" name="new_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="retype_password" class="control-label col-lg-4"><strong>Retype Password</strong></label>
                            <div class="col-lg-7">
                                <input type="password" id="pass2" class="form-control" name="retype_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters"  onkeyup="checkPass(); return false;">
                                <span id="msg" class="label label-danger"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-4">
                                <button type="submit" name="action" value="change_election_commissioner_password" class="btn btn-warning"><i class="fa fa-check"></i> Change Password</button>
                            </div>
                        </div>
                    </fieldset>
                    <!-- BEGIN ERROR BOX --> 
                    <div class="col-lg-12">
                        <% String err = (String) request.getAttribute("err");
                            String err1 = (String) request.getParameter("err");
                            if (err != null && !err.equals("") && !err.equals("null")) {%>
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=err%>
                        </div>
                        <% } else if (err1 != null && !err1.equals("") && !err1.equals("null")) {%>
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=err1%>
                        </div>
                        <%}
                            String msg = (String) request.getAttribute("msg");
                            String msg1 = (String) request.getParameter("msg");
                            if (msg != null && !msg.equals("") && !msg.equals("null")) {%>
                        <div class="alert alert-info">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=msg%>
                        </div>
                        <%} else if (msg1 != null && !msg1.equals("") && !msg1.equals("null")) {
                        %>
                        <div class="alert alert-info">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <%=msg1%>
                        </div>
                        <%}%>
                    </div>
                    <!-- END ERROR BOX -->  
            </div>
        </div>
        <!-- EDITING BY HARDIK END-->
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