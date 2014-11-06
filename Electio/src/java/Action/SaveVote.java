/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplVoter;
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
            
            try {
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                System.out.println("email : "+email + " election_type : "+election_type + " election_id : "+req.getSession().getAttribute("election_id"));
                if (req.getSession().getAttribute("election_id") == null) {
                    err = "Fail to locate election id, please retry";
                } else {
                    
                    long id = Long.parseLong(req.getSession().getAttribute("election_id").toString());
                    System.out.println("Yes");
                    view = "voted.jsp";
                    System.out.println("Yes");
                    title = "Voted";
                    System.out.println("Yes");
                    if (election_type == 2) {
                        System.out.println("Election ID: " + id);
                        try {
//                        DBDAOImplementation obj = DBDAOImplementation.getInstance();

                            if (objC.saveVote(id, candidate_email) && objV.updateVoterStatus(id, email)) {//update votes in candidate and update voter status as voted
                                msg = "Your Vote has been counted, thank you!!";
                            }
                        } catch (SQLException ex) {
                            err = ex.getMessage();
                            System.out.println("Save vote Err: " + ex.getMessage());
                        }
                    } else if (election_type == 1) {
                        System.out.println("yyyyyyy");
                        try {
                            System.out.println("yyyyyyy");
//                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                            ArrayList<Candidate> candidates = objC.getCandidates(id);
                            System.out.println("yyyyyyy");
                            for (Candidate c : candidates) {
                                System.out.println("email:"+c.getEmail()+ "votes: "+ req.getParameter(c.getEmail()));
                                c.setVotes(Long.parseLong(req.getParameter(c.getEmail())));
                            }
                            System.out.println("yyyyyyy");
                            if (objC.updateCandidateVotes(candidates, id) && objV.updateVoterStatus(id, email)) {//update votes in candidate and update voter status as voted
                                msg = "Your Vote has been counted, thank you!!";
                            }
                            System.out.println("yyyyyyy");
                        } catch (SQLException ex) {
                            System.out.println("Err: "+ex.getMessage());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: SaveVote" + e.getMessage());
                System.out.println("Errr: saveVote" + e.getMessage());
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
