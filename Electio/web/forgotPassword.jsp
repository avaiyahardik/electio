<jsp:include page="header.jsp"/>
<title>Recover Password</title>

<div class="row">
    <div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
        <div class="login-box clearfix animated flipInY">
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
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h1 class="panel-title">Recover Password</h1>
                </div>
                <div class="panel-body">
                    <form action="Controller" method="post">
                        <div class="form-group">
                            <input type="email" name="email" placeholder="Email" class="form-control user" required pattern="[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$" title="Enter a valid email address"/>
                        </div>
                        <div class="form-group">
                            <button type="submit"  name="action" value="reset_election_commissioner_password" class="btn btn-primary">Reset</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>