package Action;

import DAO.DBDAOImplCandidate;
import Utilities.EmailSender;
import Utilities.RandomString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CandidateResetPassword implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getParameter("email");
        String elec_id = req.getParameter("election_id");
        String view = "index.jsp?election_id=" + elec_id;
        String msg = null;
        String err = null;
        String title = "Forgot Password";
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Email Id required";
        } else {
            try {
                long elecion_id = Long.parseLong(elec_id);
                String newPassword = RandomString.generateRandomPassword();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                if (objC.isValidEmail(email, elecion_id)) {
                    if (EmailSender.sendMail(RandomString.ELECTIO_GMAIL_EMAIL, RandomString.ELECTIO_GMAIL_PASSWORD, "New Password", newPassword, email)) {
                        newPassword = RandomString.encryptPassword(newPassword);
                        objC.changeCandidatePassword(email, newPassword);
                        msg = "Password sent to your email successfully";
                    } else {
                        err = "Fail to send password, retry";
                    }
                } else {
                    err = "Invalid email id";
                }

            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
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
