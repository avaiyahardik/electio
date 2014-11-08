<jsp:include page="header.jsp"/>
<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<title>Voting now</title>

<div class="col-md-12">
    <div class="page-header">
        <h1 class="page-title">You are voting for <span class="label label-info"><%=request.getAttribute("election_name")%></span> </h1>
    </div>
    <div class="col-lg-8 col-lg-offset-2"><h4>Give your preference to each candidate</h4></div>
    <form class="form-horizontal" action="Controller"  method="GET">
        <input type="hidden" name="type" value="1">
        <!-- Candidate List -->
        <% ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
            if (candidates == null) {
        %>
        <script> window.location = "index.jsp";</script>
        <%
            }
            for (Candidate c : candidates) {
        %>


        <div class="col-lg-8 col-lg-offset-2">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">
                        <input type="radio" name="candidate_email" value="<%=c.getEmail()%>">
                    </span>
                    <label class="form-control"><%= c.getFirstname()%> <%= c.getLastname()%></label>
                </div>
            </div>
        </div>

        <%
            }
        %>
        <div class="col-lg-8 col-lg-offset-2">
            <div class="form-group">
                <button type="submit" name="action" value="save_vote" class="btn btn-success"><i class="fa fa-thumbs-o-up"></i> Caste Your Vote !!</button>
                <button type="reset" name="clear" class="btn btn-danger"><i class="fa fa-eraser"></i> Clear Selection</button>
            </div>
        </div>

    </form>
</div>
<jsp:include page="footer.jsp"/>