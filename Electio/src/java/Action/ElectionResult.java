/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
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

        String view = "electionResult.jsp";
        String title = "Election Result";
        String msg = null;
        String err = null;
        String elec_id = req.getParameter("election_id");
        if (elec_id == null || elec_id.equals("")) {
            err = "Election Id is missing";
            view = "index.jsp";
            title = "Login";
        } else {
            try {
                long election_id = Long.parseLong(elec_id);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                int election_type = (int) objE.getElectionType(election_id).getType_id();
                req.setAttribute("election_type", election_type);
                ArrayList<Candidate> candidates = null;
                if (election_type == 1) {
                    candidates = objC.getCandidatesForPreferentialVoting(election_id);
                } else if (election_type == 2) {
                    candidates = objC.getCandidatesForWeightedVoting(election_id);
                }
                req.setAttribute("candidates", candidates);
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
