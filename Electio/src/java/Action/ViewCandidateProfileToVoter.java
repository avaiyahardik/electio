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
 * @author Hardik Avaiya
 */
public class ViewCandidateProfileToVoter implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String view = "login.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        String email = (String) req.getSession().getAttribute("voter_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        System.out.println(email + ", " + elec_id);
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "candidateProfile.jsp";
            title = "Candidate Profile";
            Candidate c = null;
            Organization o = null;
            try {
                long id = Long.parseLong(elec_id);
                String candidate_email = req.getParameter("candidate_email");
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                c = objC.getCandidate(id, candidate_email);
                if (c != null) {
                    o = objO.getOrganization(c.getOrganization_id());
                } else {
                    view = "header.jsp";
                    title = "Voter";
                    err = "Candidate does not exists for this election";
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Candidate Profile to Voter Err: " + ex.getMessage());
            }
            req.setAttribute("candidate", c);
            req.setAttribute("organization", o);
        }
        System.out.println("Err: " + err);
        System.out.println("MSG: " + msg);
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
