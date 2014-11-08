/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplementation;
import Model.Election;
import Model.ElectionCommissioner;
import Utilities.RandomString;
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
public class Unlock implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String old_action = req.getParameter("old_action");
        String title = "Screen Locked";
        String msg = null;
        String err = null;
        String view = "lockScreen.jsp";
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                ArrayList<Election> elections = null;
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                password = RandomString.encryptPassword(password);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                if (objEC.loginElectionCommissioner(email, password)) {
                    view = "dashboard.jsp";
                    title = "Dashboard";
                    elections = objE.getCompletedElections(email);
                } else {
                    err = "Invalid password";
                }
                req.setAttribute("elections", elections);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("LockScreen Err: " + ex.getMessage());
            }
        }
        req.setAttribute("old_action", old_action);
        req.setAttribute("name", name);
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
