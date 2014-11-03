/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.ProbableNominee;
import Model.Voter;
import Utilities.EmailSender;
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
        String email = (String) req.getSession().getAttribute("email");
        String election_id = req.getParameter("election_id");

        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (election_id == null || election_id.equals("")) {
                err = "invalid parameter";
            } else {
                long id = Long.parseLong(election_id);
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    String voters[] = obj.getVotersEmail(id);
                    String link = "<a href='" + req.getServletContext().getInitParameter("domain_base") + "voter/login.jsp?election_id=" + election_id + "'>" + req.getServletContext().getInitParameter("domain_base") + "voter/login.jsp?election_id=" + election_id + "</a>";
                    if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Registration Link", link, voters)) {
                        System.out.println("mail send to all voters ");
                        msg = "mail send to all voters";
                    } else {
                        err = "Fail to send mail to voters";
                        System.out.println("Fail to send mail to voters");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SendMailToVoters.class.getName()).log(Level.SEVERE, null, ex);
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