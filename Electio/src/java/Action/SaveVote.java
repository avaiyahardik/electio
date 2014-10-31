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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class SaveVote implements Controller.Action{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("voter_email");
        String election_id = (String) req.getSession().getAttribute("election_id");
        String election_type=req.getParameter("type");
        String candidate_email=req.getParameter("candidate_email");
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
                    if(obj.saveVote(id, candidate_email) && obj.updateVoterStatus(id, email)){//update votes in candidate and update voter status as voted
                        msg="Saved Your vote successfully";
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("Save vote Err: " + ex.getMessage());
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
