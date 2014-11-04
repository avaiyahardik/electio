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
            title = "Nominee/Candidate Profile";
            long id = Long.parseLong(elec_id);
            try {
//                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();

                Election e = objE.getElection(id);
                Nominee n = objN.getNominee(id, email);
                Candidate c = objC.getCandidate(id, email);
                req.setAttribute("nominee", n);
                req.setAttribute("candidate", c);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Nominee/Candidate Profile Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
