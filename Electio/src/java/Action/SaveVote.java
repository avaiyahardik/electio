/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class SaveVote implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("voter_email");
        long election_type = (Long) req.getSession().getAttribute("election_type");
        String candidate_email = req.getParameter("candidate_email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (req.getSession().getAttribute("election_id") == null) {
                err = "Fail to locate election id, please retry";
            } else {
                System.out.println("Yes");
                long id = Long.parseLong(req.getSession().getAttribute("election_id").toString());
                view = "voted.jsp";
                if (election_type == 1) {                    
                    System.out.println("Election ID: " + id);
                    try {
                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                        if (obj.saveVote(id, candidate_email) && obj.updateVoterStatus(id, email)) {//update votes in candidate and update voter status as voted
                            msg = "Your Vote has been counted, thank you!!";
                        }
                    } catch (SQLException ex) {
                        err = ex.getMessage();
                        System.out.println("Save vote Err: " + ex.getMessage());
                    }
                } else if (election_type == 2) {
                    try {
                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                        ArrayList<Candidate> candidates = obj.getCandidates(id);
                        for (Candidate c : candidates) {
                            c.setVotes(Long.parseLong(req.getParameter(c.getEmail())));
                        }
                        if (obj.updateCandidateVotes(candidates, id) && obj.updateVoterStatus(id, email)) {//update votes in candidate and update voter status as voted
                            msg = "Your Vote has been counted, thank you!!";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SaveVote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
