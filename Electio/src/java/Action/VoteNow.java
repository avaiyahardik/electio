/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplVoter;
import Model.Candidate;
import Model.Election;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        String elec_type = (String) req.getSession().getAttribute("election_type");
        String elec_id = (String) req.getSession().getAttribute("election_id");

        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        System.out.println("email: " + email + ", elecType: " + elec_type + ", elec Id: " + elec_id);
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {
            try {
                long id = Long.parseLong(elec_id);
                long election_type = Long.parseLong(elec_type);
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                Election election = objE.getElection(id);
                boolean status = objV.getVoterStatus(id, email);
                Date date = new Date();
                view = "voted.jsp";
                if (date.before(election.getVoting_start())) {
                    msg = "Voting period has not been started";
                } else if (date.after(election.getVoting_end())) {
                    msg = "Voting period got over";
                    if (status) {
                        msg += ", you voted";
                    } else {
                        msg += ", you didn't voted";
                    }
                } else {
                    if (status == false) {
                        ArrayList<Candidate> candidates = objC.getCandidates(id);
                        req.setAttribute("candidates", candidates);
                        req.setAttribute("election_name", election.getName());
                        title = "Voting Now";
                        if (election_type == 2) {
                            view = "weighted.jsp";
                            title = "Weighted Voting";
                        } else if (election_type == 1) {
                            view = "preferential.jsp";
                            title = "Preferential Voting";
                        }
                    } else if (status == true) {
                        msg = "You have already voted for this election, thank you!!"; // message should be displayed on view page
                    }
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Candidate Detail Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
