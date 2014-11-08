/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplNominee;
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
public class NomineeChangePassword implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("candidate_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
//        System.out.println("Email: " + email + ", Elec Id: " + elec_id);
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "Controller?action=candidate_profile";
            title = "Nominee/Candidate Profile";

            String old_password = req.getParameter("old_password");
            String new_password = req.getParameter("new_password");
            String retype_password = req.getParameter("retype_new_password");
            if (old_password == null || old_password.equals("") || new_password == null || new_password.equals("") || retype_password == null || retype_password.equals("")) {
                err = "All fields are mendatory";
            } else {
                long election_id = Long.parseLong(elec_id);
                try {
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    if (objN.nomineeLogin(election_id, email, old_password)) {
                        if (new_password.equals(retype_password)) {
                            new_password = RandomString.encryptPassword(new_password);
                            if (objN.changeNomineePassword(email, new_password)) {
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
                    System.out.println("NomineeChangePassword Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
