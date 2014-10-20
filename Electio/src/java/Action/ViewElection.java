/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import DAO.DBDAOImplementation;
import Model.Election;
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
public class ViewElection implements Controller.Action{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email=req.getSession().getAttribute("email").toString();
        String view="listElections.jsp";
        ArrayList<Election>  el=new ArrayList<Election>();
        try {
            DBDAOImplementation obj=DBDAOImplementation.getInstance();
            el=obj.getElection(email);
        } catch (SQLException ex) {
            Logger.getLogger(ViewElection.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("Election", el);
        return view;
    }
    
}
