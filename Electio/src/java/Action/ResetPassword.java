package Action;

import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplementation;
import Utilities.EmailSender;
import Utilities.RandomString;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetPassword implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getParameter("email");
        String view = "forgotPassword.jsp";
        String msg = null;
        String err = null;
        System.out.println("EMAIL: " + email);
        String title = "Forgot Password";
        if (email == null || email.equals("")) {
            err = "Email Id required";
        } else {
            try {
                String newPassword = RandomString.generateRandomPassword(); // it'll generate new password
//                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                DBDAOImplElectionCommissioner objEC = DBDAOImplElectionCommissioner.getInstance();
                String s[] = {email};
                if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "New Password", newPassword, s)) {
                    //newPassword = RandomString.encryptPassword(newPassword);
                    objEC.changeElectionCommissionerPassword(email, newPassword); // it'll update password
                    msg = "Password Sent To your email successfully";
                } else {
                    err = "Some error occur";
                }
                // write a code to send email containing password
                // stored in "newPassword" variable to election commissioner
                // at email id stored in "email" variable.
                // On successful sent of email set msg="Password sent successfully to <email id>"
                // on failure set err="Fail to send email at <email>"
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Forgot Password Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
