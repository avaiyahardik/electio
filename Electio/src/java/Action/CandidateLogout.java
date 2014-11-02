package Action;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CandidateLogout implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String email = (String) req.getSession().getAttribute("candidate_email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                req.getSession().invalidate();
                msg = "You're logged out successfully";
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Candidate Logout Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
