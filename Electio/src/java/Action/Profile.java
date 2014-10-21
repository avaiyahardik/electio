/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Election;
import Model.ElectionCommissioner;
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
public class Profile implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getSession().getAttribute("email").toString();
        String view = "profile.jsp";
        req.setAttribute("title", "Profile");
        ElectionCommissioner ec = new ElectionCommissioner();
        try {
            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            ec = obj.getElectionCommissioner(email);

        } catch (SQLException ex) {
            Logger.getLogger(ViewElection.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("ec", ec);
        return view;

    }

}
