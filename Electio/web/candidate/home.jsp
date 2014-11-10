<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.Election"%>
<jsp:include page="header.jsp"/>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Election el = (Election) request.getAttribute("election");
    int status = (Integer) request.getAttribute("nominee_status");
    String reason = (String) request.getAttribute("reason");
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
        <label style="font-size: 17px;">You nomination status</label>
        <%if (status == 0) {%>
        <label style="font-size: 17px;" class="label label-info control-label" style="font-size:13px"><i class="fa fa-clock-o"></i> Waiting</label>
        <%} else if (status == 1) {%>
        <label style="font-size: 17px;" class="label label-success control-label" style="font-size:13px"><i class="fa fa-check"></i> Approved</label>
        <%} else if (status == 2) {%>
        <label style="font-size: 17px;" class="label label-danger control-label" style="font-size:13px"><i class="fa fa-times"></i> Rejected (<%= reason%>)</label>
        <%} else if (status == 3) {%>
        <label style="font-size: 17px;" class="label label-warning control-label" style="font-size:13px"><i class="glyphicon glyphicon-thumbs-down"></i> Withdrawn</label>
        <%}%>

        <%if (el.getWithdrawal_start().before(new Date()) && el.getWithdrawal_end().after(new Date()) && status != 3 && status != 2) {%>
        <a href="Controller?action=withdraw_application" class="btn btn-warning btn-sm"><i class="glyphicon glyphicon-thumbs-down"></i> Withdraw Application</a>
        <%}%>

</div>


<jsp:include page="footer.jsp"/>