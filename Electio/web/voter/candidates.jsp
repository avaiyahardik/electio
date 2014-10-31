<%@page import="Model.Candidate"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp"/>

<table class="table table-hover">
    <thead>
        <tr>
            <th>Candidate Name</th>
            <th>Organization</th>
            <th>Gender</th>
        </tr>
    </thead>

    <!-- Candidate List here -->

    <% ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
    for(Candidate c : candidates){
    %>
    <tr>
        <td><a href="#"><%=c.getFirstname()%> <%=c.getLastname()%></a></td>
        <td></td>
        <td></td>
    </tr>
    
    <%}%>

</table>

<jsp:include page="footer.jsp"/>