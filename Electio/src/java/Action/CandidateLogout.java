package Action;

import java.io.IOException;
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
            view = "../index.jsp?electio=electio";
            err = "You are not logged in, or session already expired";
        } else {
            try {
                long election_id = Long.parseLong(elec_id);
                req.getSession().invalidate();
                view += "&election_id=" + election_id;
                msg = "You're logged out successfully";
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (Exception ex) {
                err = "Could not complete action";
                System.out.println("Candidate Logout Error: " + ex.getMessage());
            }
        }
        view += "&msg=" + msg + "&err=" + err + "&title=" + title;
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        try {
            res.sendRedirect(view);
        } catch (IOException ex) {
            System.out.println("Candidate Logout Fail to redirect" + ex.getMessage());
        }
        return view;
    }
}
