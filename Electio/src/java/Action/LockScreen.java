/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.ElectionCommissioner;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class LockScreen implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = req.getSession().getAttribute("email").toString();
        String view = "lockScreen.jsp";
        String name = null;

        try {
            DBDAOImplementation obj = DBDAOImplementation.getInstance();
            ElectionCommissioner ec = obj.getElectionCommissioner(email);
            name = ec.getFirstname()+ " " + ec.getLastname();
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewElection.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("name", name);
        return view;
    }

}
