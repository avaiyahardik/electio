package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import DAO.DBDAOImplementation;
import Model.Candidate;
import Model.Election;
import Model.ElectionType;
import Model.Nominee;
import Model.Voter;
import Utilities.EmailSender;
import Utilities.RandomString;
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
public class WithdrawApplication implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String elec_id = (String) req.getSession().getAttribute("election_id");
        String email = (String) req.getSession().getAttribute("candidate_email");

        String view = "index.jsp";  // default view should be login page itself
        String title = "Login";
        String msg = null;
        String err = null;
        System.out.println(elec_id + ", " + email);
        if (email.equals("") || email == null) {
            err = "session expired"; // error message should be displayed on view page
        } else {
            if (elec_id.equals("") || elec_id == null) {
                err = "invalid parameter";
            } else {
                long election_id = Long.parseLong(elec_id);
                try {
//                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                    DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                    DBDAOImplElection objE = DBDAOImplElection.getInstance();
                    DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                    if (objN.withdrawMyApplication(election_id, email)) {
                        view = "home.jsp";
                        title = "Nominee/Candidate Home Page";
                        req.getSession().setAttribute("election_id", elec_id);
                        req.getSession().setAttribute("candidate_email", email);
                        Nominee n = objN.getNominee(election_id, email);
                        req.getSession().setAttribute("candidate_name", n.getFirstname());
                        Election e = objE.getElection(election_id);
                        req.setAttribute("election", e);
                        int nominee_status = objN.getNomineeStatus(election_id, email);
                        req.setAttribute("nominee_status", nominee_status);
                        System.out.println("Name: " + n.getFirstname());
                    }
                } catch (SQLException ex) {
                    err = ex.getMessage(); // error message should be displayed on the view page
                    System.out.println("withdraw application Err: " + ex.getMessage());
                }
            }
        }
        req.setAttribute("msg", msg); // setting msg attribute
        req.setAttribute("err", err); // setting err attribute
        req.setAttribute("title", title);
        return view;
    }
}
