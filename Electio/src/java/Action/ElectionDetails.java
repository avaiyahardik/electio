package Action;

import DAO.DBDAOImplElection;
import Model.Election;
import java.sql.SQLException;
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
        long election_type = (Long) req.getSession().getAttribute("election_type");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired please login again";
        } else {
            if (req.getSession().getAttribute("election_id") == null) {
                err = "Fail to locate election id, please retry";
            } else {
                view = "electionDetails.jsp";
                long election_id = Long.parseLong(req.getSession().getAttribute("election_id").toString());
                try {
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    Election el = objE.getElection(election_id);
                    req.setAttribute("election", el);
                } catch (SQLException ex) {
                    err = ex.getMessage();
                    System.out.println("VoterLogin SQL Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg); // setting msg attribute
        req.setAttribute("err", err); // setting err attribute
        req.setAttribute("title", title);
        return view;
    }
}
