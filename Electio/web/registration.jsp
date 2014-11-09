<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="DAO.DBDAOImplCandidate"%>
<%@page import="Model.Organization"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DBDAOImplementation"%>
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
                                    <input type="text" class="form-control" name="mobile" required>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="select_organization_name" class="col-lg-4 control-label"><strong>Organization</strong></label>
                            <div class="col-lg-7">
                                <select name="organization_id" class="form-control"  id="org-id" onChange="checkOrg(this.value)">
                                    <option value="">-- Select --</option>
                                    <%
                                        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                                        ArrayList<Organization> orgs = objO.getAllOrganizations();
                                        for (Organization org : orgs) {
                                    %>
                                    <option value="<%=org.getId()%>"><%= org.getName()%></option>
                                    <%
                                        }
                                    %>
                                    <option value="0">Other</option>
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
                            <label for="password" class="control-label col-lg-4"><strong>Password</strong></label>
                            <div class="col-lg-7">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue">
                                        <span class="arrow"></span>
                                        <i class="fa fa-key"></i> 
                                    </span>
                                    <input type="password" class="form-control" name="password" required>
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
                                    <input type="password" class="form-control" name="retype_password" required>
                                </div>
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
</script>
<jsp:include page="footer.jsp"/>