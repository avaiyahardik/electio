/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionCommissioner;
import Model.Nominee;
import Model.Organization;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik Avaiya
 */
public class ViewCandidateProfileToVoter implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "candidateProfile.jsp";
            title = "Candidate Profile";
            long id = Long.parseLong(elec_id);
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                Election e = obj.getElection(id);
                Candidate c = obj.getCandidate(id, email);
                req.setAttribute("candidate", c);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Candidate Profile to Voter Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
