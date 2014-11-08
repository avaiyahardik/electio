/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplementation;
import Model.Election;
import Model.ElectionCommissioner;
import Model.Organization;
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
public class ElectionCommissionerUpdateProfile implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
// update profile abhi pura baki he
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

            try {
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                ElectionCommissioner ec = objEC.getElectionCommissioner(email);
                Organization org = objO.getOrganization(ec.getOrganization_id());
                req.setAttribute("election_commissioner", ec);
                req.setAttribute("organization", org);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("ECUpdateProfile Err: " + ex.getMessage());
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
