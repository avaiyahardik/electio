package Action;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ElectionCommissionerLogout implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        System.out.println("EMAIL: " + email);
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                req.getSession().invalidate();
                msg = "You're logged out successfully";
                res.sendRedirect("../index.jsp?msg="+msg);
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
