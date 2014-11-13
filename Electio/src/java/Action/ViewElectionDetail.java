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
            String tab = (String) req.getParameter("tab");
            if (elec_id == null || elec_id.equals("") || tab == null || tab.equals("")) {
                err = "Election id or tab name missing";
            } else {
                try {
                    long id = Long.parseLong(elec_id);
                    view = "electionDetail.jsp";
                    title = "Election Details";
                    objE = DBDAOImplElection.getInstance();
                    objN = DBDAOImplNominee.getInstance();
                    objC = DBDAOImplCandidate.getInstance();
                    objV = DBDAOImplVoter.getInstance();
                    objP = DBDAOImplEligibleNominee.getInstance();
                    if (elec_id == null || !objE.isValidElectionId(id, email) || tab == null || tab.equals("")) {
                        view = "listElections.jsp";
                        title = "Elections";
                        ArrayList<Election> elections = null;
                        elections = objE.getElections(email);
                        req.setAttribute("elections", elections);
                        err = "Fail to locate election id, please retry";
                    } else {
                        System.out.println("Election ID: " + id);
                        System.out.println("tab : " + tab);

                        Election el = objE.getElection(id, email);
                        req.setAttribute("election", el);
                        ArrayList<Candidate> result_candidates = null;
                        if (el.getType_id() == 2) {
                            result_candidates = objC.getCandidatesForWeightedVoting(id);
                        } else {
                            result_candidates = objC.getCandidatesForPreferentialVoting(id);
                        }

                        ArrayList<Candidate> candidates = objC.getCandidates(id);
                        switch (tab) {
                            case "general":
                                view = "electionDetail.jsp";
                                title = "General Election Details";
                                break;
                            case "statistics":
                                view = "electionStatistics.jsp";
                                title = "Election Statistics";
                                req.setAttribute("candidates", result_candidates);
                                break;
                            case "nominees":
                                view = "electionNominees.jsp";
                                title = "Nominees";
                                ArrayList<Nominee> nominees = objN.getNominees(id);
                                req.setAttribute("nominees", nominees);
                                break;
                            case "candidates":
                                view = "electionCandidates.jsp";
                                title = "Candidates";
                                req.setAttribute("candidates", candidates);
                                break;
                            case "voters":
                                view = "electionVoters.jsp";
                                title = "Voters";
                                ArrayList<Voter> voters = objV.getVoters(id);
                                req.setAttribute("voters", voters);
                                break;
                            case "probable_nominees":
                                view = "electionProbableNominees.jsp";
                                title = "Eligible Nominees";
                                ArrayList<EligibleNominee> pns = objP.getAllProbableNominees(id);
                                req.setAttribute("probable_nominee", pns);
                                break;
                            default:
                                view = "listElections.jsp";
                                title = "Elections";
                                ArrayList<Election> elections = null;
                                elections = objE.getElections(email);
                                req.setAttribute("elections", elections);
                                err = "Requesting invalid tab";
                        }
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
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
