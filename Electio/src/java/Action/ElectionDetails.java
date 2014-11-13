package Action;

import DAO.DBDAOImplElection;
import Model.Election;
import java.sql.SQLException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class ElectionDetails implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String email = (String) req.getSession().getAttribute("voter_email");
        String elec_id = (String) req.getSession().getAttribute("election_id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("") || elec_id == null || elec_id.equals("")) {
            err = "Session expired or you are not logged in, please login";
        } else {
            view = "electionDetails.jsp";
            try {
                long election_id = Long.parseLong(elec_id);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                Election el = objE.getElection(election_id);
                req.setAttribute("election", el);
            } catch (NumberFormatException ex) {
                err = "Invalid election id";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = ex.getMessage();
                System.out.println("VoterLogin SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
