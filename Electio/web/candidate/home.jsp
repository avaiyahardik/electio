<%@page import="DAO.DBDAOImplementation"%>
<%@page import="Model.Election"%>
<jsp:include page="header.jsp"/>
<%
    Election el = (Election) request.getAttribute("election");
    int nominee_status = (Integer) request.getAttribute("nominee_status");
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
            <td><%=el.getNomination_start()%></td>
            <td><%=el.getNomination_end()%></td>
        </tr>

        <tr class="danger">
            <td>Withdrawal</td>
            <td><%=el.getWithdrawal_start()%></td>
            <td><%=el.getWithdrawal_end()%></td>
        </tr>

        <tr class="success">
            <td>Voting</td>
            <td><%=el.getVoting_start()%></td>
            <td><%=el.getVoting_end()%></td>
        </tr>
    </table>
    <h1>You nomination status => <%=nominee_status%></h1>
</div>


<jsp:include page="footer.jsp"/>