/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import Model.Election;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik Avaiya
 */
public class CandidateHome implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("candidate_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired or you are not logged in, please login";
        } else {
            view = "home.jsp";
            title = "Home Page";
            long id = Long.parseLong(elec_id);
            try {
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                Election e = objE.getElection(id);
                int status = objN.getNomineeStatus(id, email);
                req.setAttribute("election", e);
                req.setAttribute("nominee_status", status);
                if (status == 2) {
                    String reason = objN.getReason(id, email);
                    req.setAttribute("reason", reason);
                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Candidate Home Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
