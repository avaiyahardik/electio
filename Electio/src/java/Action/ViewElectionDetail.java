/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplVoter;
import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionCommissioner;
import Model.Nominee;
import Model.Organization;
import Model.ProbableNominee;
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
public class ViewElectionDetail implements Controller.Action {

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
            if (req.getParameter("id") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Fail to locate election id, please retry";
            } else {
                System.out.println("Yes");
                long id = Long.parseLong(req.getParameter("id"));
                view = "electionDetail.jsp";
                title = "Election Detail";
                System.out.println("Election ID: " + id);
                try {
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                    DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                    Election el = objE.getElection(id);
                    req.setAttribute("election", el);
                    ArrayList<Nominee> nominees = objN.getNominees(id);
                    req.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = objC.getCandidates(id);
                    req.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = objV.getVoters(id);
                    req.setAttribute("voters", voters);
                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(id);
                    req.setAttribute("probable_nominee", pns);

                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("View Election Detail Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }

}
