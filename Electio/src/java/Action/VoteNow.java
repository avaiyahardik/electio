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
import Model.Voter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class VoteNow implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("voter_email");

        long election_type = (Long) req.getSession().getAttribute("election_type");
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
                
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    long id = Long.parseLong(req.getSession().getAttribute("election_id").toString());
                    boolean status = obj.getVoterStatus(id, email);
                    if (status == false) {
                        
                        System.out.println("type->" + election_type);

                        ArrayList<Candidate> candidates = obj.getCandidates(id);
                        req.setAttribute("candidates", candidates);

                        title = "Voting Now";
                        if (election_type == 1) {
                            view = "weighted.jsp";
                            System.out.println("Election ID: " + id);

                        } else if (election_type == 2) {
                            view = "preferential.jsp";
                        }
                    } else if (status == true) {
                        view = "voted.jsp";
                        msg = "You have already voted for this election, thank you!!"; // message should be displayed on view page
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("View Candidate Detail Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
