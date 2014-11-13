<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="DAO.DBDAOImplCandidate"%>
<%@page import="Model.Organization"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>
<script src="js/script.js"></script>
<title>Election Commissioner Registration</title>

<div class="page-title">
    <h1>Election Commissioner Registration</h1>
</div>
<hr>

<div class="row">
    <div class="col-sm-8 col-sm-offset-2">
        <div class="panel panel-default">
            <div class="panel-heading bg-blue">
                <h3 class="panel-title">Election Commissioner Registration</h3>
            </div>

            <div class="panel-body">
                <form action="Controller" method="POST" class="form-horizontal">
                    <fieldset>
                        <div class="form-group">
                            <!-- BEGIN ERROR BOX -->

                            <%
                                String msg = (String) request.getAttribute("msg");
                                if (msg != null) {
                            %>

                            <div class="alert alert-info">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=msg%>
                            </div>
                            <% }%>

                            <%
                                String err = (String) request.getAttribute("err");
                                if (err != null) {
                            %>
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <%=err%>

                            </div>
                            <% }%>
                            <!-- END ERROR BOX -->  
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-4" for="firstname"><strong>First Name</strong></label>
                            <div class="col-sm-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-user"></i> 
                                    </span>
                                    <input type="text" name="firstname" class="form-control" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="lastname"><strong>Last Name</strong></label>
                            <div class="col-sm-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-user"></i> 
                                    </span>
                                    <input type="text" name="lastname" class="form-control" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-lg-4"><strong>Email ID</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i>@</i> 
                                    </span>
                                    <input type="email" name="email" class="form-control" required>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="mobile" class="col-lg-4 control-label"><strong>Mobile No</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-mobile">+91</i> 
                                    </span>
                                    <input type="text" class="form-control" name="mobile" required pattern="[7-9]{1}[0-9]{9}" title="10 Digit mobile number">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="select_organization_name" class="col-lg-4 control-label"><strong>Organization</strong></label>
                            <div class="col-lg-7">
                                <select name="organization_id" class="form-control"  id="org-id" onChange="checkOrg(this.value)">
                                    <option value="-1">-- Select --</option>
                                    <%
                                        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                                        ArrayList<Organization> orgs = objO.getAllOrganizations();
                                        for (Organization o : orgs) {
                                    %>
                                    <option value="<%=o.getId()%>"><%= o.getName()%></option>
                                    <%
                                        }
                                    %>
                                    0                     <option value="0">Other</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" id="org-name" style="display:none">
                            <label for="organization_name" class="control-label col-lg-4"><strong>Organization Name</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-info"></i> 
                                    </span>
                                    <input type="text" class="form-control" name="organization_name">
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="org-address" style="display:none">
                            <label for="organization_address" class="control-label col-lg-4"><strong>Organization Address</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-envelope-o"></i> 
                                    </span>
                                    <input type="text" class="form-control" name="organization_address">
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="org-about" style="display:none">
                            <label for="about_organization" class="control-label col-lg-4"><strong>About Organization</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-tag"></i> 
                                    </span>
                                    <input type="text" class="form-control" name="about_organization">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="control-label col-lg-4" ><strong>Password</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-key"></i> 
                                    </span>
                                    <input type="password" class="form-control" id="pass1" name="password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="retype_password" class="control-label col-lg-4"><strong>Retype Password</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-key"></i> 
                                    </span>
                                    <input type="password" class="form-control" id="pass2" name="retype_password" required pattern=".{8,14}" title="Min 8 & Max 14 Characters" onkeyup="checkPass(); return false;">
                                </div>
                                <span id="msg" class="label label-danger"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-4">
                                <button type="submit" name="action" value="election_commissioner_registration" class="btn btn-primary"><i class="fa fa-check"></i> Register</button>
                                <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Reset</button>
                            </div>
                        </div>
                    </fieldset>

                </form>
            </div>
        </div>
    </div>
</div>

<script src="assets/readable/jquery-1.10.2.min.js"></script>
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