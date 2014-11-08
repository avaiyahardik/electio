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

public class ResetElectionCommissionerPassword implements Controller.Action {

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
                if (EmailSender.sendMail(RandomString.ELECTIO_JAINTELE_EMAIL, RandomString.ELECTIO_JAINTELE_PASSWORD, "New Password", newPassword, email)) {
                    newPassword = RandomString.encryptPassword(newPassword);
                    objEC.changeElectionCommissionerPassword(email, newPassword); // it'll update password
                    msg = "Password sent to your email successfully, login now";
                } else {
                    err = "Fail to send password, please retry";
                }
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
