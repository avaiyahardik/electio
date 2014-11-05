/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplVoter;
import DAO.DBDAOImplementation;
import Model.Candidate;
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
public class SendMailToVoters implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("hi");
        String email = (String) req.getSession().getAttribute("email");
        String election_id = req.getParameter("election_id");

        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        System.out.println("Elec ID: " + election_id + ", " + email);
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (election_id == null || election_id.equals("")) {
                err = "invalid parameter";
            } else {
                System.out.println("hi");
                long id = Long.parseLong(election_id);
                try {
//                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                    ArrayList<Voter> voters = objV.getVotersEmail(id);
                    String link = "<a href='" + RandomString.DOMAIN_BASE + "voter/login.jsp?election_id=" + election_id + "'>" + RandomString.DOMAIN_BASE + "voter/login.jsp?election_id=" + election_id + "</a>";
                    for (Voter v : voters) {
                        if (v.getLinkStatus() == false) {
                            if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Ballot Link", link, v.getEmail())) {
                                System.out.println("mail send to all voters ");
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
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("SendMailToVoter Err: " + ex.getMessage());
                }

                view = "Controller?action=dashboard";
                title = "Dashboard";
            }
        }

        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
