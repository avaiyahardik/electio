/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import Model.Election;
import Model.Nominee;
import Model.Organization;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String elec_id = req.getParameter("election_id");
            String nominee_email = req.getParameter("email");
            try {
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                if (elec_id == null || elec_id.equals("") || nominee_email == null || nominee_email.equals("")) {
                    view = "listElections.jsp";
                    title = "Elections";
                    ArrayList<Election> elections = null;
                    elections = objE.getElections(email);
                    req.setAttribute("elections", elections);
                    err = "Fail to locate election id or nominee email, please retry";
                } else {
                    long id = Long.parseLong(elec_id);
                    view = "nomineeDetails.jsp";
                    title = "Nominee Details";
                    Nominee n = objN.getNominee(id, nominee_email);
                    Organization org = objO.getOrganization(n.getOrganization_id());
                    req.setAttribute("nominee", n);
                    req.setAttribute("organization", org);

                }
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Nominee Detail Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
