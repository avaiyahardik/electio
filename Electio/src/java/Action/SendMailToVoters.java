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
import Utilities.EmailSender;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class SendMailToVoters implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String DOMAIN_BASE = req.getRequestURL().substring(0, req.getRequestURL().indexOf("Electio") + 8);
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("election_id");

        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        System.out.println("Elec ID: " + elec_id + ", " + email);
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (elec_id == null || elec_id.equals("")) {
                err = "invalid parameter";
            } else {

                DBDAOImplElection objE = null;
                DBDAOImplNominee objN = null;
                DBDAOImplCandidate objC = null;
                DBDAOImplVoter objV = null;
                DBDAOImplEligibleNominee objP = null;
                long election_id = 0;
                try {
                    objE = DBDAOImplElection.getInstance();
                    objN = DBDAOImplNominee.getInstance();
                    objC = DBDAOImplCandidate.getInstance();
                    objV = DBDAOImplVoter.getInstance();
                    objP = DBDAOImplEligibleNominee.getInstance();
                    ArrayList<Election> elections = null;
                    elections = objE.getElections(email);
                    req.setAttribute("elections", elections);
                    view = "listElections.jsp";
                    title = "Elections";
                    election_id = Long.parseLong(elec_id);
                    if (!objE.isValidElectionId(election_id, email)) {
                        err = "Invalid election id";
                    } else {
                        ArrayList<Voter> voters = objV.getVotersEmail(election_id);
                        String link = "<a href='" + DOMAIN_BASE + "voter/index.jsp?election_id=" + election_id + "'>" + DOMAIN_BASE + "voter/index.jsp?election_id=" + election_id + "</a>";
                        for (Voter v : voters) {
                            if (v.getLinkStatus() == false) {
                                if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Ballot Link", link, v.getEmail())) {
                                    msg = "mail send to all voters";
                                    v.setLinkStatus(true);
                                    System.out.println("Mail sent to: " + v.getEmail());
                                    objV.changeVoterLinkStatus(v);
                                } else {
                                    err = "Fail to send mail to voters";
                                    System.out.println("Fail to send mail to voters: " + v.getEmail());
                                }
                            }
                        }
                        Election el = objE.getElection(election_id);
                        req.setAttribute("election", el);
                        view = "electionDetail.jsp";
                        title = "Election Detail";
                    }
                } catch (NumberFormatException ex) {
                    err = "Invalid election id";
                    System.out.println("NFE: " + ex);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("SendMailToVoter Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
