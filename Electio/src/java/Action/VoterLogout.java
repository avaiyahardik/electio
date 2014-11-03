package Action;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoterLogout implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String email = (String) req.getSession().getAttribute("voter_email");
        String view = "login.jsp?election_id=" + elec_id;
        String msg = null;
        String err = null;
        String title = "Voter Login";
        if (elec_id == null || email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                req.getSession().invalidate();
                msg = "You're logged out successfully";
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Voter Logout Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
