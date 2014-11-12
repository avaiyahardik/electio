/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplOrganization;
import Model.Candidate;
import Model.Organization;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class ViewCandidateDetails implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            System.out.println("2");
            if (req.getParameter("election_id") == null || req.getParameter("email") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Fail to locate election id or nominee email, please retry";
            } else {
                System.out.println("3");
                long id = Long.parseLong(req.getParameter("election_id"));
                String candidate_email = req.getParameter("email");
                view = "candidateDetails.jsp";
                title = "Candidate Details";
                try {
//                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                    Candidate c = objC.getCandidate(id, candidate_email);
                    Organization org = objO.getOrganization(c.getOrganization_id());
                    req.setAttribute("candidate", c);
                    req.setAttribute("organization", org);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("View Nominee Detail Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
