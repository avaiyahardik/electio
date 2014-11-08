<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="headerSidebar.jsp"/>


<%  ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
    int election_type = (Integer) request.getAttribute("election_type");%>

<div id="main-content">
    <div class="page-title">
        <h3><strong>Election Results</strong></h3>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading bg-blue">
                    <h3 class="panel-title">Election Results</h3>
                </div>            
                <div class="panel body">
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <h3 class="panel-title">Final Preferences of the Candidates</h3>
                        </div>
                    </div>
                    <%if (election_type == 1) {
                            int cnt = 0;
                            for (Candidate c : candidates) {
                                cnt++;%>
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="input-group">
                                <span class="input-group-addon bg-blue"><%=cnt%></span>
                                <label class="form-control"><strong><%= c.getFirstname()%> <%= c.getLastname()%></strong></label>
                            </div>
                        </div>
                    </div>
                    <%}
                        }%>
                </div>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="../js/charts.js"></script>
<script type="text/javascript" src="../js/jsapi"></script>

<jsp:include page="footer.jsp"/>