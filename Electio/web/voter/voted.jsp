<jsp:include page="header.jsp"/>


<%  String msg = (String) request.getAttribute("msg");
    if (msg == null || msg.equals("")) {
%>
<script>
    window.location = "index.jsp";
</script>
<%}%>
<div class="col-md-12">
    <div class="page-header">
        <h1 class="page-title">Voted!</h1>
    </div>

    <div class="alert alert-info">
        <h1><%=msg%></h1>
    </div>
</div>

<jsp:include page="footer.jsp"/>