/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import Model.Candidate;
import java.sql.SQLException;
import java.util.ArrayList;
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

        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {

            view = "candidates.jsp";
            title = "Candidates List";
            ArrayList<Candidate> candidates = null;
            try {
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                long election_id = Long.parseLong(elec_id);
                candidates = objC.getCandidates(election_id);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("List Candidates Err: " + ex.getMessage());
            }
            req.setAttribute("candidates", candidates);
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
