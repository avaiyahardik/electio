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
import Utilities.RandomString;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionCommissionerLogin implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String view = "index.jsp";
        String title = "Login";
        String msg = null;
        String err = null;
        System.out.println(email + ", " + password);
        if (email == null || email.equals("") || password == null || password.equals("")) {
            err = "Please fill-up required fields";
        } else {
            password = RandomString.encryptPassword(password);
            try {
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                ArrayList<Election> elections = null;
                if (objEC.loginElectionCommissioner(email, password)) {
                    req.getSession().setAttribute("email", email);
                    view = "dashboard.jsp";
                    title = "Dashboard";
                    elections = objE.getCompletedElections(email);
                } else {
                    err = "Invalid email or password, please retry";
                }
                req.setAttribute("elections", elections);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Login SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
