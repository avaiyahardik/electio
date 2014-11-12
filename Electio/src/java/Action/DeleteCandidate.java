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
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Model.ProbableNominee;
import Model.Voter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class DeleteCandidate implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String msg = null;
        String err = null;
        String view = "index.jsp";
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired, please login again";
        } else {
            String elec_id = req.getParameter("election_id");
            String candidate_email = req.getParameter("email");
            if (elec_id == null || elec_id.equals("") || candidate_email == null || candidate_email.equals("")) {
                view = "listElections.jsp";
                title = "Elections";
                ArrayList<Election> elections = null;
                try {
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    elections = objE.getElections(email);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("ViewElections Err: " + ex.getMessage());
                }
                req.setAttribute("elections", elections);
                err = "Fail to locate election id or candidate email, please retry";
            } else {
                view = "electionDetail.jsp";
                title = "Election Detail";
                long election_id = Integer.parseInt(elec_id);
                DBDAOImplElection objE = null;
                DBDAOImplNominee objN = null;
                DBDAOImplCandidate objC = null;
                DBDAOImplVoter objV = null;
                DBDAOImplProbableNominee objP = null;
                try {
                    objE = DBDAOImplElection.getInstance();
                    objN = DBDAOImplNominee.getInstance();
                    objC = DBDAOImplCandidate.getInstance();
                    objV = DBDAOImplVoter.getInstance();
                    objP = DBDAOImplProbableNominee.getInstance();

                    if (objC.deleteCandidate(election_id, candidate_email)) {
                        msg = "Candidate deleted successfully";
                        String reason = req.getParameter("reason");
                        objN.rejectNominee(election_id, candidate_email, reason);
                    } else {
                        err = "Fail to delete candidate, please retry";
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("Delete candidate Error: " + ex.getMessage());
                }
                try {
                    Election el = objE.getElection(election_id);
                    req.setAttribute("election", el);
                    ArrayList<Nominee> nominees = objN.getNominees(election_id);
                    req.setAttribute("nominees", nominees);
                    ArrayList<Candidate> candidates = objC.getCandidates(election_id);
                    req.setAttribute("candidates", candidates);
                    ArrayList<Voter> voters = objV.getVoters(election_id);
                    req.setAttribute("voters", voters);
                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(election_id);
                    req.setAttribute("probable_nominee", pns);

                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("DeleteCandidate setting election data: " + ex.getMessage());
                }
            }
        }

        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
