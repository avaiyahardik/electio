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
import Model.Nominee;
import Model.ProbableNominee;
import Model.Voter;
import Utilities.EmailSender;
import Utilities.RandomString;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class SendMailToProbableNominee implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("election_id");

        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (elec_id == null || elec_id.equals("")) {
                err = "invalid parameter";
            } else {
                long election_id = Long.parseLong(elec_id);
                view = "electionDetail.jsp";
                title = "Election Detail";
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

                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(election_id);
                    String link = "<a href='" + RandomString.DOMAIN_BASE + "candidate/nomineeRegistration.jsp?election_id=" + election_id + "'>" + RandomString.DOMAIN_BASE + "candidate/index.jsp?election_id=" + election_id + "</a>";
                    for (ProbableNominee itm : pns) {
                        if (itm.getStatus() == 0) {
                            if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Registration Link", link, itm.getEmail())) {
                                itm.setStatus(1);
                                objP.changeProbableNomineeStatus(itm);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Err SendMailToNominee: " + ex.getMessage());
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
                    System.out.println("SendMailToElegibleNominee setting election data: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
