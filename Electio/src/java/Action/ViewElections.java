/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

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
 * @author darshit
 */
public class ViewElections implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getSession().getAttribute("email").toString();
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            view = "listElections.jsp";
            title = "View Elections";
            ArrayList<Election> elections = null;
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                elections = obj.getElections(email);
                System.out.println("Total: " + elections.size());
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("ViewElections Err: " + ex.getMessage());
            }
            req.setAttribute("elections", elections);
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
