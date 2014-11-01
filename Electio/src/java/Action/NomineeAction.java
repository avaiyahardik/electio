 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplementation;
import Util.EmailSender;
import java.io.File;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class NomineeAction implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

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
                view = "Controller?action=view_election_detail&id=" + election_id;
                title = "Election Detail";
                String nominee_email = req.getParameter("email");
                try {
                    DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    String[] to = {nominee_email};
                    if (cmd.equals("approve")) {
                        String requirements_file = req.getParameter("requirements_file");
                        if (obj.approveNominee(election_id, nominee_email, requirements_file)) {
                            String ms = "Your Nomination is approved. To see your details goto Below link <a href='localhost:8084/Electio/candidate/index.jsp'>" + req.getContextPath() + File.separator + "candidate" + File.separator + "index.jsp?election_id=" + election_id + "</a>";
                            System.out.println("MS: " + ms);
                            req.getSession().setAttribute("election_id", election_id);
                            EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Approval", ms, to);

                            msg = "Nominee approved successfully";
                        } else {
                            err = "Error occured while approving nominee, please try again";
                        }

                    } else if (cmd.equals("reject")) {
                        String reason = req.getParameter("reason");
                        if (obj.rejectNominee(election_id, nominee_email, reason)) {
                            EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Nominee Rejection", "You nomination is rejected", to);
                            msg = "Nominee rejeced successfully";
                        } else {
                            err = "Error occured while rejecting nominee, please try again";
                        }
                    }
                } catch (Exception ex) {
                    err = ex.getMessage();
                    System.out.println("RejectNominee Err: " + ex.getMessage());

                }

            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
