/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import Model.Election;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class Dashboard implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired or you are not logged in, please login";
        } else {
            view = "dashboard.jsp";
            title = "Dashboard";
            ArrayList<Election> elections = null;
            try {
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                elections = objE.getCompletedElections(email);
            } catch (SQLException ex) {
                err = "Could not complete action";
                System.out.println("ViewElections Err: " + ex.getMessage());
            }
            req.setAttribute("elections", elections);
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
