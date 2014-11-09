<jsp:include page="header.jsp"/>
<title>Recover Password</title>

<div class="row">
    <div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
        <div class="login-box clearfix animated flipInY">
            <!-- BEGIN ERROR BOX -->
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
            <!-- END ERROR BOX -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h1 class="panel-title">Recover Password</h1>
                </div>
                <div class="panel-body">
                    <form action="Controller" method="post">
                        <div class="form-group">
                            <input type="text" name="email" placeholder="Email" class="form-control user" required/>                        
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