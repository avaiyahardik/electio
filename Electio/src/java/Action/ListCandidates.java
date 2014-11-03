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
public class ListCandidates implements Controller.Action {

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

            view = "candidates.jsp";
            title = "Candidates List";

            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                long election_id = Long.parseLong(elec_id);
                ArrayList<Candidate> candidates = obj.getCandidates(election_id);
                req.setAttribute("candidates", candidates);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("List Candidates Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
