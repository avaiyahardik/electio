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
import Model.EligibleNominee;
import Model.Voter;
import Utilities.EmailSender;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class NomineeAction implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String DOMAIN_BASE = req.getRequestURL().substring(0, req.getRequestURL().indexOf("Electio") + 8);

        String email = (String) req.getSession().getAttribute("email");
        String title = "Login";
        String msg = null;
        String err = null;
        String view = "index.jsp";
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            String cmd = req.getParameter("cmd");

            if (cmd == null || req.getParameter("election_id") == null || req.getParameter("email") == null) {
                view = "Controller?action=view_elections";
                title = "Elections";
                err = "Insufficient parameters";
            } else {
                long election_id = Long.parseLong(req.getParameter("election_id"));
                view = "electionDetail.jsp";
                title = "Election Detail";
                String nominee_email = req.getParameter("email");
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

                    if (cmd.equals("approve")) {
                        String requirements_file = req.getParameter("requirements_file");
                        if (objN.approveNominee(election_id, nominee_email, requirements_file)) {
                            String ms = "Your Nomination is approved. To see your details goto Below link <a href='" + DOMAIN_BASE + "candidate/index.jsp?election_id=" + election_id + "'>" + DOMAIN_BASE + "candidate/index.jsp?election_id=" + election_id + "</a>";
                            EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Approval", ms, nominee_email);
                            msg = "Nominee approved successfully";
                        } else {
                            err = "Error occured while approving nominee, please try again";
                        }
                    } else if (cmd.equals("reject")) {

                        String reason = req.getParameter("reason");
                        System.out.println("Elec: " + election_id + ", nom_email: " + nominee_email + ", Reason: " + reason);
                        if (objN.rejectNominee(election_id, nominee_email, reason)) {
                            EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Rejection", "You nomination is rejected", nominee_email);
                            msg = "Nominee rejeced successfully";
                        } else {
                            err = "Error occured while rejecting nominee, please try again";
                        }
                    }
                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("NomineeAction Err: " + ex.getMessage());
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
                    ArrayList<EligibleNominee> pns = objP.getAllProbableNominees(election_id);
                    req.setAttribute("probable_nominee", pns);

                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("NomineeAction setting election data: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
