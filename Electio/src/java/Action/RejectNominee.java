/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class RejectNominee implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String title = "Login";
        String msg = null;
        String err = null;
        String view = "index.jsp";
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            if (req.getParameter("election_id") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Could not locate election id";
            } else {
                long election_id = Long.parseLong(req.getParameter("election_id"));
                view = "Controller?action=view_election_detail&id=" + election_id;
                title = "Election Detail";

                if (req.getParameter("email") == null) {
                    err = "Could not locate nominee email";
                } else {
                    String nominee_email = req.getParameter("email");
                    try {
                        DBDAOImplementation obj = DBDAOImplementation.getInstance();
                        if (obj.rejectNominee(election_id, nominee_email)) {
                            msg = "Nominee rejeced successfully";
                        } else {
                            err = "Error occured while rejecting nominee, please try again";
                        }
                    } catch (SQLException ex) {
                        err = ex.getMessage();
                        System.out.println("RejectNominee Err: " + ex.getMessage());
                    }
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
