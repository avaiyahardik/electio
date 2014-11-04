/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.ProbableNominee;
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
//                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    DBDAOImplProbableNominee objP=DBDAOImplProbableNominee.getInstance();
                    ArrayList<ProbableNominee> pns = objP.getAllProbableNominees(id);
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
