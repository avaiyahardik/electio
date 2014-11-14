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

        String view = "index.jsp";
        String title = "Home";
        String msg = null;
        String err = null;

        System.out.println(email_id + ", " + first_name + ", " + mobile_no + ", " + message);
        if (email_id == null || email_id.equals("") || first_name == null || first_name.equals("") || mobile_no == null || mobile_no.equals("") || message == null || message.equals("")) {
            err = "Please fill-up required fields";
        } else {
            String mesg = "<br>Email : " + email_id + " <br>Name: " + first_name + "<br>Mobile No: " + mobile_no + "<br><p>"+message+"<p>";
            if (EmailSender.sendMail(RandomString.ELECTIO_JAINTELE_EMAIL, RandomString.ELECTIO_JAINTELE_PASSWORD, "Feedback", mesg, "sen.daiict@gmail.com")) {
                msg = "feedback sent successfully";
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;

    }
}
