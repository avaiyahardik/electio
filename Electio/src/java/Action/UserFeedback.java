package Action;

import Utilities.EmailSender;
import Utilities.RandomString;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class UserFeedback implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email_id = req.getParameter("email_id");
        String first_name = req.getParameter("first_name");
        String mobile_no = req.getParameter("mobile_no");
        String message = req.getParameter("message");

        String view = "registration.jsp";
        String title = "Registration";
        String msg = null;
        String err = null;

        System.out.println(email_id + ", " + first_name + ", " + mobile_no + ", " + message);
        if (email_id == null || email_id.equals("") || first_name == null || first_name.equals("") || mobile_no == null || mobile_no.equals("") || message == null || message.equals("")) {
            err = "Please fill-up required fields";
        } else {
            String mesg = "email_id : " + email_id + " Name: " + first_name + " Mobile No: " + mobile_no;
            if (EmailSender.sendMail(RandomString.ELECTIO_GMAIL_EMAIL, RandomString.ELECTIO_GMAIL_PASSWORD, "Feedback", mesg, "sen.daiict@gmail.com")) {
                msg = "feedback mail successfully";
            }
            /*try {
             DBDAOImplementation obj = DBDAOImplementation.getInstance();

             Feedback_info fd = new Feedback_info(email_id, first_name, mobile_no, message);
             if (obj.feedback(fd)) {
             msg = "You're registered successfully";
             view = "index.jsp";
             title = "Login";
             } else {
             err = "Fail to send message, please try again later";
             System.out.println("Fail to send message, please try again later");
             }
             } catch (SQLException ex) {
             err = ex.getMessage();
             System.out.println("Feedback send SQL Err: " + ex.getMessage());
             }*/
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }
}
