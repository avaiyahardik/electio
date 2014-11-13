package Action;

import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import Model.Election;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class WithdrawApplication implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String elec_id = (String) req.getSession().getAttribute("election_id");
        String email = (String) req.getSession().getAttribute("candidate_email");

        String view = "index.jsp";
        String title = "Login";
        String msg = null;
        String err = null;
//        System.out.println(elec_id + ", " + email);
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired or you are not logged in, please login";
        } else {
            try {
                long election_id = Long.parseLong(elec_id);
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                if (objN.withdrawMyApplication(election_id, email)) {
                    view = "home.jsp";
                    title = "Home Page";
                    Election e = objE.getElection(election_id);
                    req.setAttribute("election", e);
                    req.setAttribute("nominee_status", 3 + "");
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("withdraw application Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
