/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionCommissioner;
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
public class VoterHome implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("voter_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || elec_id == null) {
            err = "Session expired please login again";
        } else {

            view = "electionDetails.jsp";
            title = "Election Details";

            try {
                long election_id = Long.parseLong(elec_id);
                //DBDAOImplementation obj = DBDAOImplementation.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                Election el = objE.getElection(election_id);
                req.setAttribute("election", el);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Voter Home Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
