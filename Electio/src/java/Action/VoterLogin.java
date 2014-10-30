package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.ElectionType;
import Util.RandomString;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class VoterLogin implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String elec_id = req.getParameter("election_id");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String view = "login.jsp";  // default view should be login page itself
        String title = "Voter Login";
        String msg = null;
        String err = null;
        System.out.println(elec_id + ", " + email + ", " + password);
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("") || password == null || password.equals("")) {
            err = "Insufficiant input"; // error message should be displayed on view page
        } else {
            long election_id = Long.parseLong(elec_id);
            password = RandomString.encryptPassword(password);
            System.out.println("Encrypted password: " + password);
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                if (obj.loginVoter(election_id, email, password)) {
                    view = "voting.jsp"; // view changed if login successfull
                    title = "Voting";
                    msg = "You're logged in successfully"; // message should be displayed on view page
                    req.getSession().setAttribute("election_id", elec_id);
                    req.getSession().setAttribute("email", email);
                    ArrayList<Candidate> candidates = obj.getCandidates(election_id);
                    ElectionType election_type = obj.getElectionType(election_id);
                    req.setAttribute("candidates", candidates);
                    req.setAttribute("election_type", election_type);
                } else {
                    err = "Fail to login, please retry"; // error message should be displayed on view page
                }
            } catch (SQLException ex) {
                err = ex.getMessage(); // error message should be displayed on the view page
                System.out.println("VoterLogin SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg); // setting msg attribute
        req.setAttribute("err", err); // setting err attribute
        req.setAttribute("title", title);
        return view;
    }
}
