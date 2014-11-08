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
                                <input type="text" name="email" class="form-control" required value="<%=ec.getEmail()%>">
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
                                    <input type="text" class="form-control" required="required" name="mobile"  value="<%=ec.getMobile()%>">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="organization_name" class="control-label col-lg-5"><strong>Organization Name</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" required="required" name="organization_name"  value="<%= organization.getName()%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="organization_address" class="control-label col-lg-5"><strong>Organization Address</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" required="required" name="organization_address" value="<%= organization.getAddress()%>">
                            </div>
                        </div>
                        <div class="form-group">
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
                                <input type="password" class="form-control" name="old_password" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="new_password" class="control-label col-lg-4"><strong>Password</strong></label>
                            <div class="col-lg-7">
                                <input type="password" class="form-control" name="new_password" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="retype_password" class="control-label col-lg-4"><strong>Retype Password</strong></label>
                            <div class="col-lg-7">
                                <input type="password" class="form-control" name="retype_password" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-4">
                                <button type="submit" name="action" value="change_election_commissioner_password" class="btn btn-warning"><i class="fa fa-check"></i> Change Password</button>
                            </div>
                        </div>
                    </fieldset>

                    <!-- EDITING BY HARDIK START -->
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
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
                    </div>
                    <!-- EDITING BY HARDIK END-->
                </form>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>