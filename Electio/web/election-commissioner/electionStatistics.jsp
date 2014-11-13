<%@page import="Utilities.RandomString"%>
<%@page import="Model.User"%>
<%@page import="DAO.DBDAOImplUser"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<!-- BEGIN PAGE LEVEL STYLE -->
<link rel="stylesheet" href="../assets/plugins/magnific/magnific-popup.css">    
<!-- END PAGE LEVEL STYLE -->
<%
    String DOMAIN_BASE = request.getRequestURL().substring(0, request.getRequestURL().indexOf("Electio") + 8);
    Election el = (Election) request.getAttribute("election");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<div id="main-content">
    <div class="page-title">
        <h3><strong>
                <%= el.getName()%>
            </strong></h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading align-center">
                    <div class="btn-group">
                        <a href="Controller?action=view_election_detail&id=1&tab=general" class="btn btn-info"><i class="fa fa-info-circle"></i> General</a>
                        <a href="Controller?action=view_election_detail&id=1&tab=nominees" class="btn btn-info"><i class="fa fa-user"></i> Nominees</a>
                        <a href="Controller?action=view_election_detail&id=1&tab=candidates" class="btn btn-info"><i class="fa fa-user"></i> Candidates</a>
                        <a href="Controller?action=view_election_detail&id=1&tab=voters" class="btn btn-info"><i class="fa fa-group"></i> Voters</a>
                        <a href="Controller?action=view_election_detail&id=1&tab=probable_nominees" class="btn btn-info"><i class="fa fa-list"></i> Probable Nominees</a>
                        <a href="#" class="btn btn-primary"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>

                <div class="panel-body">
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
                </div>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/plugins/magnific/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript" src="../js/charts.js"></script>
<script type="text/javascript" src="../js/jsapi"></script>
<!-- END  PAGE LEVEL SCRIPTS -->

<script type="text/javascript">

    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Candidate');
        data.addColumn('number', 'Votes');
    <%
        int total_votes = 0;
        ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
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