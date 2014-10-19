/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.ElectionCommissioner;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionCommissionerRegistration implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String mobile = req.getParameter("mobile");
        String organization = req.getParameter("organization");
        String password = req.getParameter("password");
        String view = "registration.jsp";
        String msg = "";
        String err = "";
        System.out.println(email + ", " + firstname + ", " + lastname + ", " + mobile + ", " + organization + ", " + password);
        if (email == null || email.equals("") || firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || mobile == null || mobile.equals("") || organization == null || organization.equals("") || password == null || password.equals("")) {
            err = "Please fill-up required fields";
        } else {
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                ElectionCommissioner ec = new ElectionCommissioner(email, firstname, lastname, mobile, organization, password);
                if (obj.registerElectionCommissioner(ec)) {
                    msg = "You're registered successfully";
                    view = "index.jsp";
                } else {
                    err = "Fail to register, please try again later";
                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("Register SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        return view;
    }
}
