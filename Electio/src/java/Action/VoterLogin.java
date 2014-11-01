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
public class VoterLogin implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String elec_id = req.getParameter("election_id");
        String email = req.getParameter("email");
        String step = req.getParameter("step");
        String password = "";
        String view = "index.jsp";  // default view should be login page itself
        String title = "";
        String msg = null;
        String err = null;
        System.out.println(elec_id + ", " + email);
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("")) {
            err = "Insufficiant input"; // error message should be displayed on view page
        } else {
            long election_id = Long.parseLong(elec_id);
            //   password = RandomString.encryptPassword(password);
            //  System.out.println("Encrypted password: " + password);
            try {
                DBDAOImplementation obj = DBDAOImplementation.getInstance();
                if (step.equals("1")) {
                    if (obj.loginVoter1(election_id, email) != null) {
                        Voter v = obj.loginVoter1(election_id, email);
                        view="login.jsp?election_id=" + election_id;
                        msg = "You have already voted for this election, thank you!!"; // message should be displayed on view page
                        if (v.getStatus() == false) {
                            view = "login2.jsp"; // view changed if email exists and status is not voted successfull
                            title = "";
                            password = RandomString.generateRandomPassword();
                            obj.insertVoterPassword(election_id, email, password);

                            req.setAttribute("election_id", elec_id);
                            req.setAttribute("email", email);
                            String[] to = {email};
                            if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Password", password, to)) {
                                msg = "Your password has been sent to your email id";
                            } else {
                                msg = "Server Error, try again after sometime";
                                view = "login.jsp?election_id=" + election_id;
                            }
                    } else {

                        err = "Fail to login, please retry"; // error message should be displayed on view page
                    }
                    } 
                } else if (step.equals("2")) {
                    password = req.getParameter("password");
                    if (obj.loginVoter2(election_id, email, password)) {
                        Voter v = obj.loginVoter1(election_id, email);
                        msg = "Already voted. wait for result"; // message should be displayed on view page
                        if (v.getStatus() == false) {
                            view = "electionDetails.jsp"; // view changed if login successfull
                            title = "Election Details";
                            Election el = obj.getElection(election_id);
                            ArrayList<Candidate> candidates = obj.getCandidates(election_id);
                            ElectionType election_type = obj.getElectionType(election_id);
                            req.setAttribute("candidates", candidates);
                            req.getSession().setAttribute("election_type", election_type.getType_id());
                            req.setAttribute("election", el);
                            req.getSession().setAttribute("election_id", election_id);
                            req.getSession().setAttribute("voter_email", email);
                        }
                    } else {
                        err = "Fail to login, please retry"; // error message should be displayed on view page
                    }
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
