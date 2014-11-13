/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplOrganization;
import Model.Candidate;
import Model.Election;
import Model.Organization;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class ViewCandidateDetails implements Controller.Action {

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
            String candidate_email = req.getParameter("email");
            try {
                long id = 0;
                if (elec_id == null || candidate_email == null) {
                    view = "listElections.jsp";
                    title = "Elections";
                    ArrayList<Election> elections = null;
                    try {
                        DBDAOImplElection objE = DBDAOImplElection.getInstance();
                        elections = objE.getElections(email);
                    } catch (SQLException ex) {
                        err = ex.getMessage();
                        System.out.println("ViewCandidate-ViewElections Err: " + ex.getMessage());
                    }
                    req.setAttribute("elections", elections);
                    err = "Fail to locate election id or nominee email, please retry";
                } else {
                    id = Long.parseLong(elec_id);
                    view = "candidateDetails.jsp";
                    title = "Candidate Details";

                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    DBDAOImplOrganization objO = DBDAOImplOrganization.getInstance();
                    Candidate c = objC.getCandidate(id, candidate_email);
                    Organization org = objO.getOrganization(c.getOrganization_id());
                    req.setAttribute("candidate", c);
                    req.setAttribute("organization", org);
                }

            } catch (NumberFormatException ex) {
                view = "listElections.jsp";
                title = "Elections";
                ArrayList<Election> elections = null;
                try {
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    elections = objE.getElections(email);
                } catch (SQLException ex2) {
                    err = ex.getMessage();
                    System.out.println("ViewCandidate-nfe-ViewElections Err: " + ex2.getMessage());
                }
                req.setAttribute("elections", elections);
                err = "Fail to locate election id or nominee email, please retry";
                System.out.println("NFE: " + ex);
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
