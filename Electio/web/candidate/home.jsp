<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.Election"%>
<jsp:include page="header.jsp"/>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Election el = (Election) request.getAttribute("election");
    int status = (Integer) request.getAttribute("nominee_status");
%>

<div class="page-header">
    <h1 class="page-title">Election - <%=el.getName()%></h1>
</div>

<div class="well">
    <h4><%=el.getDescription()%></h4><br>


    <table class="table table-striped table-hover ">

        <thead>
        <th colspan="3">Election Timeline</th>
        </thead>

        <thead>
        <th></th>
        <th>Start Date</th>
        <th>End Date</th>
        </thead>

        <tr class="info">
            <td>Nomination</td>
            <td><%=sdf.format(new Date(el.getNomination_start().getTime()))%></td>
            <td><%=sdf.format(new Date(el.getNomination_end().getTime()))%></td>
        </tr>

        <tr class="danger">
            <td>Withdrawal</td>
            <td><%=sdf.format(new Date(el.getWithdrawal_start().getTime()))%></td>
            <td><%=sdf.format(new Date(el.getWithdrawal_end().getTime()))%></td>
        </tr>

        <tr class="success">
            <td>Voting</td>
            <td><%=sdf.format(new Date(el.getVoting_start().getTime()))%></td>
            <td><%=sdf.format(new Date(el.getVoting_end().getTime()))%></td>
        </tr>
    </table>
    <h1>You nomination status 
        <%if (status == 0) {%>
        <label class="label label-warning control-label" style="font-size:13px"><i class="fa fa-clock-o"></i> Waiting</label>
        <%} else if (status == 1) {%>
        <label class="label label-success control-label" style="font-size:13px"><i class="fa fa-check"></i> Approved</label>
        <%} else {%>
        <label class="label label-danger control-label" style="font-size:13px"><i class="fa fa-times"></i> Rejected</label>
        <%}%>
    </h1>
</div>


<jsp:include page="footer.jsp"/>