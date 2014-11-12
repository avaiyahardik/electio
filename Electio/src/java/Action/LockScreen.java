/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElectionCommissioner;
import Model.ElectionCommissioner;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class LockScreen implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String title = "Screen Locked";
        String msg = null;
        String err = null;
        String view = "lockScreen.jsp";
        String name = null;
        try {
            DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
            ElectionCommissioner ec = objEC.getElectionCommissioner(email);
            name = ec.getFirstname() + " " + ec.getLastname();
        } catch (SQLException ex) {
            err = ex.getMessage();
            System.out.println("LockScreen Err: " + ex.getMessage());
        }
        req.setAttribute("name", name);
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
