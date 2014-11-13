<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp"/>
<%  ArrayList<Candidate> candidates = null;
    long type = 0;
    try {
        candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
        String elec_type = (String) request.getAttribute("election_type");
        type = Integer.parseInt(elec_type);
    } catch (Exception ex) {
        out.print("No candidates for this election");
    } %>

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
                            if (cnt == 0) { %>
                        <label class="form-control"><strong>No Candidates for this election available</strong></label>
                        <%}
                        } else {%>
                        <div class="form-group">
                            <div class="col-lg-8 col-lg-offset-2">
                                <h3 class="panel-title">Election Results</h3>
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

    <div class="row">
        <div class="col-lg-12">
            <a href="#"  id="link-toggle">File a Petition</a>
        </div>
    </div>

    <div class="row" id="petition-form" style="display:none">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">File a Petition</div>
                <div class="panel-body">
                    <form action="Controller" method="POST" class="form-horizontal">
                        <fieldset>
                            <div class="form-group">
                                <label class="control-label col-lg-8">Please give description about the Petition</label>
                            </div>
                            <div class="form-group">
                                <div class=" col-lg-12">
                                    <textarea class="form-control" required name="description"></textarea>
                                </div>
                            </div>

                            <div class="form-group"><div class=" col-lg-12">
                                    <button class="btn btn-primary" type="submit" name="action" value="file_petition"><i class="fa fa-envelope"></i> File Petition now!</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <br>

    </div>
</div>
<script type="text/javascript" src="../js/charts.js"></script>
<script type="text/javascript" src="../js/jsapi"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#link-toggle').click(function() {
            $('#petition-form').toggle(200);
        });
    });

    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Candidate');
        data.addColumn('number', 'Votes');
    <%
        int total_votes = 0;
        candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
        out.print("candidates: " + candidates);
        System.out.print("Candidates: " + candidates);
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
                var chart = new google.visualization.ColumnChart(document.getElementById('result-chart-bar'));
                chart.draw(data, options);
            }


</script>
<jsp:include page="footer.jsp"/>