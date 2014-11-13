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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionResult implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String view = "index.jsp";
        String title = "Home";
        String msg = null;
        String err = null;
        String elec_id;
        String candidate_email = (String) req.getSession().getAttribute("candidate_email");
        String voter_email = (String) req.getSession().getAttribute("voter_email");
        String ec_email = (String) req.getAttribute("email");
        System.out.println("ec_email: " + ec_email + "candidate emails " + candidate_email + ", voter email: " + voter_email);
        if (candidate_email == null && voter_email == null && ec_email == null) {
            err = "Session expired or you are  not logged in";
        } else {
            if (candidate_email == null && voter_email == null && candidate_email.equals("") && voter_email.equals("")) {
                elec_id = req.getParameter("election_id");
            } else {
                elec_id = (String) req.getSession().getAttribute("election_id");
            }
            try {
                view = "electionResult.jsp";
                title = "Election Result";
                long election_id = Long.parseLong(elec_id);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                if (elec_id == null || elec_id.equals("")) {
                    err = "Election Id is missing";
                    view = "index.jsp";
                    title = "Login";
                } else {
                    ArrayList<Candidate> candidates = null;
                    long election_type = (int) objE.getElectionType(election_id).getType_id();
                    req.setAttribute("election_type", election_type + "");
                    if (election_type == 1) {
                        candidates = objC.getCandidatesForPreferentialVoting(election_id);
                    } else if (election_type == 2) {
                        candidates = objC.getCandidatesForWeightedVoting(election_id);
                    }
                    req.setAttribute("candidates", candidates);
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election id";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("ElectionResult SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
