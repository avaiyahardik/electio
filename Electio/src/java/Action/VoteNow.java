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
        String election_id = (String) req.getSession().getAttribute("election_id");
        String election_type=req.getParameter("type");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || election_id == null || election_id.equals("")) {
            err = "Session expired please login again";
        } else {
            
                System.out.println("Yes");
                long id = Long.parseLong(election_id);
                if(election_type.equals("1")){
                view = "weighted.jsp";
                title = "";
                System.out.println("Election ID: " + id);
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    ArrayList<Candidate> candidates = obj.getCandidates(id);
                    req.setAttribute("candidates", candidates);                    
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("View Candidate Detail Err: " + ex.getMessage());
                }
                }
                else if(election_type.equals("2")){
                    
                }
            
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
