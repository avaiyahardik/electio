/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import Model.Candidate;
import Model.Election;
import Model.Organization;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String elec_id = req.getParameter("election_id");
            String candidate_email = req.getParameter("email");
            try {
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                ArrayList<Election> elections = null;
                elections = objE.getElections(email);
                req.setAttribute("elections", elections);
                view = "listElections.jsp";
                title = "Elections";
                if (elec_id == null || elec_id.equals("") || candidate_email == null || candidate_email.equals("")) {
                    err = "Fail to locate election id or nominee email, please retry";
                } else {
                    long id = Long.parseLong(elec_id);
                    if (objC.isValidEmail(candidate_email, id)) {
                        Candidate c = objC.getCandidate(id, candidate_email);
                        Organization org = objO.getOrganization(c.getOrganization_id());
                        req.setAttribute("candidate", c);
                        req.setAttribute("organization", org);
                        view = "candidateDetails.jsp";
                        title = "Candidate Details";
                    } else {
                        err = "Invalid candidate email";
                    }
                }
            } catch (NumberFormatException ex) {
                err = "Fail to locate election id or nominee email, please retry";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Nominee Detail Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
