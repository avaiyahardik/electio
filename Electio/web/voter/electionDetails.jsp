<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Election"%>
<jsp:include page="header.jsp"/>
<title>Election Details</title>

<div class="col-md-12">
    <div class="page-header">
        <h1 class="page-title"><span class="label label-info"><!-- Election Name here --></span> </h1>
    </div>

    <div class="form-horizontal">
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-1">
                <%    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    Election el = (Election) request.getAttribute("election");%>
                <table class="responsiveExpander">
                    <tr>
                        <th>Election Name </th>
                        <td>&nbsp;<%= el.getName()%></td>
                    </tr>   
                    <tr>
                        <th>Voting Start</th>
                        <td><%= sdf.format(new Date(el.getVoting_start().getTime()))%></td>
                    </tr>   
                    <tr>
                        <th>Voting End</th>
                        <td><%= sdf.format(new Date(el.getVoting_end().getTime()))%></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>