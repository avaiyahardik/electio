/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
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
public class ViewNomineeDetails implements Controller.Action {

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
            System.out.println("2");
            if (req.getParameter("election_id") == null || req.getParameter("email") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Fail to locate election id or nominee email, please retry";
            } else {
                System.out.println("3");
                long id = Long.parseLong(req.getParameter("election_id"));
                String nominee_email = req.getParameter("email");
                view = "nomineeDetails.jsp";
                title = "Nominee Details";
                try {
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                    Nominee n = objN.getNominee(id, nominee_email);
                    Organization org = objO.getOrganization(n.getOrganization_id());
                    req.setAttribute("nominee", n);
                    req.setAttribute("organization", org);
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
