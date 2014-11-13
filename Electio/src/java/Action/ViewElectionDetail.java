/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplEligibleNominee;
import DAO.DBDAOImplVoter;
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Model.EligibleNominee;
import Model.Voter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishal Jain
 */
public class ViewElectionDetail implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            DBDAOImplElection objE = null;
            DBDAOImplNominee objN = null;
            DBDAOImplCandidate objC = null;
            DBDAOImplVoter objV = null;
            DBDAOImplEligibleNominee objP = null;

            try {
                long id = Long.parseLong(req.getParameter("id"));
                view = "electionDetail.jsp";
                title = "Election Detail";
                objE = DBDAOImplElection.getInstance();
                objN = DBDAOImplNominee.getInstance();
                objC = DBDAOImplCandidate.getInstance();
                objV = DBDAOImplVoter.getInstance();
                objP = DBDAOImplEligibleNominee.getInstance();
                if (elec_id == null || !objE.isValidElectionId(id, email)) {
                    view = "listElections.jsp";
                    title = "Elections";
                    ArrayList<Election> elections = null;
                    try {
                        elections = objE.getElections(email);
                    } catch (SQLException ex) {
                        err = ex.getMessage();
                        System.out.println("ViewElections Err: " + ex.getMessage());
                    }
                    req.setAttribute("elections", elections);
                    err = "Fail to locate election id, please retry";
                } else {
                    view = "electionDetail.jsp";
                    title = "Election Detail";
                    System.out.println("Election ID: " + id);
                    Election el = objE.getElection(id, email);
                    req.setAttribute("election", el);
                    ArrayList<Nominee> nominees = objN.getNominees(id);
                    req.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = objC.getCandidates(id);
                    req.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = objV.getVoters(id);
                    req.setAttribute("voters", voters);
                    ArrayList<EligibleNominee> pns = objP.getAllProbableNominees(id);
                    req.setAttribute("probable_nominee", pns);
                }
            } catch (NumberFormatException e) {
                view = "listElections.jsp";
                title = "Elections";
                ArrayList<Election> elections = null;
                try {

                    elections = objE.getElections(email);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("ViewElections Err: " + ex.getMessage());
                }
                req.setAttribute("elections", elections);
                err = "Fail to locate election id, please retry";
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("View Election Detail Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
