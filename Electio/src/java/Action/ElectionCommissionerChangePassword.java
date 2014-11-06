/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplementation;
import Model.ElectionCommissioner;
import Model.Organization;
import Utilities.RandomString;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionCommissionerChangePassword implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "profile.jsp";
            title = "Profile";
            String old_password = req.getParameter("old_password");
            String new_password = req.getParameter("new_password");
            String retype_password = req.getParameter("retype_password");
            try {
//                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                ElectionCommissioner ec = objEC.getElectionCommissioner(email);
                Organization org = objO.getOrganization(ec.getOrganization_id());
                req.setAttribute("election_commissioner", ec);
                req.setAttribute("organization", org);
                if (objEC.loginElectionCommissioner(email, old_password)) {
                    if (new_password.equals(retype_password)) {
                        // new_password=RandomString.encryptPassword(new_password);
                        if (objEC.changeElectionCommissionerPassword(email, new_password)) {
                            msg = "Your password changed successfully";
                        } else {
                            err = "Error occured while changing your password, please retry";
                        }
                    } else {
                        err = "Confirm password does not match, please retry";
                    }
                } else {
                    err = "Old password doesn't match, please retry";
                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("ChangePassword Err: " + ex.getMessage());
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
