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
                <% Election el = (Election) request.getAttribute("election");%>
                <table class="responsiveExpander">
                    <tr>
                        <th>Election Name</th>
                        <td><%= el.getName()%></td>
                    </tr>   
                    <tr>
                        <th>Voting Start</th>
                        <td><%= el.getVoting_start()%></td>
                    </tr>   
                    <tr>
                        <th>Voting End</th>
                        <td><%= el.getVoting_end()%></td>
                    </tr>   


                </table>


            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>