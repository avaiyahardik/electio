/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

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
public class DeleteElection implements Controller.Action {

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
            view = "Controller?action=view_elections";
            title = "View Elections";
            if (req.getParameter("id") == null) {
                err = "Unable to locate election id";
            } else {
                long id = Integer.parseInt(req.getParameter("id"));
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    if (obj.deleteElection(email, id) && obj.deleteVoter(email, id) && obj.deleteNominee(email, id)) {
                        msg = "Election deleted successfully";
                    } else {
                        err = "Fail to delete election, please retry";
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("Logout Error: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
