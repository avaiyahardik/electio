package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplementation;
import Utilities.EmailSender;
import Utilities.RandomString;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CandidateResetPassword implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getParameter("email");
        String election_id = req.getParameter("election_id");
        String view = "index.jsp?election_id" + election_id;
        String msg = null;
        String err = null;
        System.out.println("EMAIL: " + email);
        String title = "Forgot Password";
        if (email == null || email.equals("")) {
            err = "Email Id required";
        } else {
            try {
                String newPassword = RandomString.generateRandomPassword();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                String s[] = {email};
                if (EmailSender.sendMail("sen.daiict@gmail.com", "#password2014", "New Password", newPassword, s)) {
                    newPassword = RandomString.encryptPassword(newPassword);
                    objC.changeCandidatePassword(email, newPassword);
                    msg = "Password Sent To your email successfully";
                } else {
                    err = "Fail to send password, retry";
                }
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Candidate Forgot Password Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
