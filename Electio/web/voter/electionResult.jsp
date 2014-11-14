<%@page import="DAO.DBDAOImplCandidate"%>
<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp"/>
<%  ArrayList<Candidate> candidates = null;
    long type = 0;
    candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
    String elec_type = (String) request.getSession().getAttribute("election_type");
    type = Integer.parseInt(elec_type);
%>

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
                    <div class="form-horizontal">
                        <%if (type == 1) {%>
                        <div class="form-group">
                            <div class="col-lg-8 col-lg-offset-2">
                                <h3 class="panel-title">Final Preferences of the Candidates</h3>
                            </div>
                        </div>
                        <%
                            int cnt = 0;
                            long lastVote = -1;
                            for (Candidate c : candidates) {
                                if (lastVote == -1 || lastVote != c.getVotes()) {
                                    cnt++;
                                }
                                lastVote = c.getVotes();
                        %>

                        <div class="form-group">
                            <div class="col-lg-8 col-lg-offset-2">
                                <div class="input-group">
                                    <span class="input-group-addon bg-blue"><%=cnt%></span>
                                    <label class="form-control"><strong><%= c.getFirstname()%> <%= c.getLastname()%></strong></label>
                                    <span class="input-group-addon bg-blue">Votes :<%=c.getVotes()%></span>
                                </div>
                            </div>
                        </div>
                        <%}
                        } else {%>
                        <div class="form-group">
                            <div class="col-lg-8 col-lg-offset-2">
                                <h3 class="panel-title">Election Results</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8 col-lg-offset-2">
                                <div id="result-chart-pie"></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8 col-lg-offset-2">
                                <div id="result-chart-bar"></div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<script type="text/javascript" src="../js/charts.js"></script>
<script type="text/javascript" src="../js/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Candidate');
        data.addColumn('number', 'Votes');
    <%
        int total_votes = 0;
        for (Candidate c : candidates) {
            total_votes += (int) c.getVotes();
    %>
        data.addRow(['<%=c.getFirstname()%> <%=c.getLastname()%>',<%=(int) c.getVotes()%>]);
    <%}%>
                var options = {
                    title: 'Election Results ' + '\nTotal Votes : ' +<%=total_votes%>,
                    is3D: true,
                    width: 800,
                    height: 600
                };
                var chart = new google.visualization.PieChart(document.getElementById('result-chart-pie'));
                chart.draw(data, options);
                var chart = new google.visualization.ColumnChart(document.getElementById('result-chart-bar'));
                chart.draw(data, options);
            }


</script>
<jsp:include page="footer.jsp"/>