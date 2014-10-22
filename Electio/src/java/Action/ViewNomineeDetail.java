/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionCommissioner;
import Model.Nominee;
import Model.Organization;
import Model.Voter;
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
public class ViewNomineeDetail implements Controller.Action {

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

            if (req.getParameter("election_id") == null || req.getParameter("email") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Fail to locate election id or nominee email, please retry";
            } else {
                long id = Long.parseLong(req.getParameter("id"));
                String nominee_email = req.getParameter("email");
                view = "nomineeDetail.jsp";
                title = "Nominee Detail";
                System.out.println("Election ID: " + id);
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    Nominee n = obj.getNominee(id, nominee_email);
                    req.setAttribute("nominee", n);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("View Nominee Detail Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
