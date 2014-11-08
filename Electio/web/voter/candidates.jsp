<%@page import="DAO.DBDAOImplOrganization"%>
<%@page import="DAO.DBDAOImplCandidate"%>
<%@page import="DAO.DBDAOImplementation"%>
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

    <%
        DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
        DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
        ArrayList<Candidate> candidates = (ArrayList<Candidate>) request.getAttribute("candidates");
        for (Candidate c : candidates) {
    %>
    <tr>
        <td><a href="Controller?action=view_candidate_to_voter&candidate_email=<%=c.getEmail()%>"><%=c.getFirstname()%> <%=c.getLastname()%></a></td>
        <td><%= objO.getOrganization(c.getOrganization_id()).getName()%></td>
        <td>
            <% String gender = "Other";
                if (c.getGender() == 1) {
                    gender = "Male";
                } else if (c.getGender() == 2) {
                    gender = "Female";
                }
                out.print(gender);
            %>
        </td>
    </tr>

    <%}%>

</table>

<jsp:include page="footer.jsp"/>