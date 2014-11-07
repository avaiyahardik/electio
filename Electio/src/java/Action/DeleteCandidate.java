/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplementation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class DeleteCandidate implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String view = "index.jsp";
        String email = (String) req.getSession().getAttribute("email");
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired, please login again";
        } else {
            String elec_id = req.getParameter("election_id");
            String candidate_email = req.getParameter("email");
            if (elec_id == null || elec_id.equals("") || candidate_email == null || candidate_email.equals("")) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Fail to locate election id or candidate email, please retry";
            } else {
                view = "Controller?action=view_election_detail&id=" + elec_id;
                title = "Election Detail";
                long id = Integer.parseInt(elec_id);
                try {
//                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    if (objC.deleteCandidate(id, candidate_email)) {
                        msg = "Candidate deleted successfully";
                    } else {
                        err = "Fail to delete candidate, please retry";
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("Delete candidate Error: " + ex.getMessage());
                }
            }
        }

        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
