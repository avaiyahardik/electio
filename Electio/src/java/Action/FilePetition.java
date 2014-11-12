/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import Model.Candidate;
import Utilities.EmailSender;
import java.io.IOException;
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
public class FilePetition implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String email = (String) req.getSession().getAttribute("candidate_email");
        String description = req.getParameter("description");
        String err = null;
        String msg = null;
        String view = "index.jsp";
        String title = "Login";
        if (email.equals("") || email == null || elec_id.equals("") || elec_id == null) {
            err = "Session expired please login again";
        } else {
            view = "electionResult.jsp?election_id=" + elec_id;
            title = "Election Result";
            if (description == null || description.equals("")) {
                err = "Please enter some description about your petition";
            } else {
                if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "File Petition", description, email)) {
                    msg = "Petition Filed successfully!";
                    // change filed_petition value here
                } else {
                    err = "Fail to send mail";
                }
            }
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
        try {
            res.sendRedirect(view);
        } catch (Exception ex) {
            err = ex.getMessage();
            System.out.println("ERR File Petition Redirection: " + ex.getMessage());
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
