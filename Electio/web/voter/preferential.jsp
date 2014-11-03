<%@page import="DAO.DBDAOImplementation"%>
<jsp:include page="header.jsp"/>
<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>


<script src="ranking.js" type="text/javascript"></script>

<div class="col-md-12">
    <div class="page-header">
        <h1 class="page-title">You are voting for <span class="label label-info"><%=request.getAttribute("election_name")%></span> </h1>
    </div>

    <div class="col-lg-8 col-lg-offset-2 alert alert-danger" id="error">
        Please give preferences to all <b>Candidates</b>
    </div>

    <div class="col-lg-8 col-lg-offset-2"><h4>Give your preference to each candidate</h4></div>

    <% ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
        if (candidates == null) {
    %>
    <script> window.location = "index.jsp";</script>
    <%
        }
        int no = candidates.size();
    %>
    <form class="form-horizontal" name="theForm" onSubmit="return checkRankings()" action="Controller" method="POST">
        <input type="hidden" name="type" value="2">
        <!-- Candidate List -->

        <%for (Candidate c : candidates) {%>
        <div class="col-lg-8 col-lg-offset-2">
            <div class="form-group">
                <div class="input-group">
                    <label class="form-control"><%= c.getFirstname()%> <%= c.getLastname()%></label>
                    <span class="input-group-addon">
                        <select name="<%=c.getEmail()%>" onchange="check();">
                            <option value="--"> -- </option>
                            <%for (int i = 1; i <= no; i++) {%>
                            <option value="<%=i%>"><%=i%></option>
                            <%}%>
                        </select>
                    </span>
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