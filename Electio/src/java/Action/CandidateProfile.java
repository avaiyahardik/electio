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
import Model.Nominee;
import Model.Organization;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik Avaiya
 */
public class CandidateProfile implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("candidate_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "profile.jsp";
            title = "Profile";
            try {
                long id = Long.parseLong(elec_id);
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                Nominee n = objN.getNominee(id, email);
                Organization o = objO.getOrganization(n.getOrganization_id());
                req.setAttribute("nominee", n);
                req.setAttribute("organization", o);
                if (n.getStatus() == 1) {
                    Candidate c = objC.getCandidate(id, email);
                    req.setAttribute("candidate", c);
                } else if (n.getStatus() == 2) {
                    String reason = objN.getReason(id, email);
                    req.setAttribute("reason", reason);
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Candidate Profile Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
