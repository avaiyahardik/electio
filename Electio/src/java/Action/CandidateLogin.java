package Action;

import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionType;
import Model.Voter;
import Util.EmailSender;
import Util.RandomString;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class CandidateLogin implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String elec_id = req.getParameter("election_id");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String view = "index.jsp";  // default view should be login page itself
        String title = "";
        String msg = null;
        String err = null;
        System.out.println(elec_id + ", " + email);
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("") || password == null || password.equals("")) {
            err = "Insufficiant input"; // error message should be displayed on view page
        } else {
            long election_id = Long.parseLong(elec_id);
            password = RandomString.encryptPassword(password);
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                if (obj.candidateLogin(election_id, email, password)) {
                    view = "home.jsp";
                    title = "Candidate Home Page";
                    req.getSession().setAttribute("election_id", election_id);
                    req.getSession().setAttribute("candidate_email", email);
                    Candidate c = obj.getCandidate(election_id, email);
                    req.setAttribute("candidate_name", c.getFirstname());
                } else {
                    view = "index.jsp";
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
