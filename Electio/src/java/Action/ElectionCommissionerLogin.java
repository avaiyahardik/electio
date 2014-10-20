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
public class ElectionCommissionerLogin implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String view = "index.jsp";  // default view should be login page itself
        String msg = null;
        String err = null;
        System.out.println(email + ", " + password);
        if (email == null || email.equals("") || password == null || password.equals("")) {
            err = "Please fill-up required fields"; // error message should be displayed on view page
        } else {
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                if (obj.loginElectionCommissioner(email, password)) {
                    req.getSession().setAttribute("email", email);
                    view = "dashboard.jsp"; // view changed if login successfull
                    msg = "You're logged in successfully"; // message should be displayed on view page
                } else {
                    err = "Fail to login, please retry"; // error message should be displayed on view page
                }
            } catch (SQLException ex) {
                err = ex.getMessage(); // error message should be displayed on the view page
                System.out.println("Login SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg); // setting msg attribute
        req.setAttribute("err", err); // setting err attribute
        return view;
    }
}
