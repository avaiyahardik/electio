package Action;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutElectionCommissioner implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getSession().getAttribute("email").toString();
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                req.getSession().invalidate();
                msg = "You're logged out successfully";
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Logout Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
