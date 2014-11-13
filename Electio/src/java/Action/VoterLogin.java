package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplVoter;
import Model.Candidate;
import Model.Election;
import Model.Voter;
import Utilities.EmailSender;
import Utilities.RandomString;
import java.io.IOException;
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
        String step = req.getParameter("step");
        String password = "";
        String view = "login.jsp?election_id=" + elec_id;
        String title = "Login";
        String msg = null;
        String err = null;
        System.out.println(elec_id + ", " + email);
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("") || step == null || step.equals("")) {
            err = "Insufficiant input";
        } else {
            password = RandomString.encryptPassword(password);
            try {
                long election_id = Long.parseLong(elec_id);
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                if (objE.isValidElectionId(election_id)) {
                    if (step.equals("1")) {
                        if (objV.loginVoter1(election_id, email) != null) {
                            Voter v = objV.loginVoter1(election_id, email);
                            view = "login2.jsp"; // view changed if email exists and status is not voted successfull
                            title = "Voter Login";
                            password = RandomString.generateRandomPassword();
                            req.setAttribute("election_id", elec_id);
                            req.setAttribute("email", email);

                            if (EmailSender.sendMail("electio@jaintele.com", "electio_2014", "Password", password, email)) {
                                password = RandomString.encryptPassword(password);
                                objV.insertVoterPassword(election_id, email, password);
                                msg = "Your password has been sent to your email id";
                            } else {
                                msg = "Fail to send mail, try again after sometime";
                                err = "Invalid login cradentials, please retry";
                                view += "&msg=" + msg + "&err=" + err + "&title=" + title + "&email=" + email;
                                try {
                                    res.sendRedirect(view);
                                } catch (IOException ex) {
                                    System.out.println("Voter Logout Fail to redirect" + ex.getMessage());
                                }
                            }
                        } else {
                            err = "Invalid email or election id";
                            view += "&msg=" + msg + "&err=" + err + "&title=" + title + "&email=" + email;
                            try {
                                res.sendRedirect(view);
                            } catch (IOException ex) {
                                System.out.println("Voter Logout Fail to redirect" + ex.getMessage());
                            }
                        }
                    } else if (step.equals("2")) {
                        password = req.getParameter("password");
                        password = RandomString.encryptPassword(password);
                        if (objV.loginVoter2(election_id, email, password)) {
                            Voter v = objV.loginVoter1(election_id, email);

                            view = "electionDetails.jsp"; // view changed if login successful
                            title = "Election Details";
                            Election el = objE.getElection(election_id);
                            ArrayList<Candidate> candidates = objC.getCandidates(election_id);
                            Election election = objE.getElection(election_id);
                            req.setAttribute("candidates", candidates);
                            System.out.println("ttppyyee" + election.getType_id());
                            req.getSession().setAttribute("election_type", election.getType_id());
                            req.setAttribute("election", el);
                            req.getSession().setAttribute("election_id", elec_id);
                            req.getSession().setAttribute("voter_email", email);
                        } else {
                            view = "login2.jsp"; // view changed if email exists and status is not voted successfull
                            title = "Voter Login";
                            req.setAttribute("election_id", elec_id);
                            req.setAttribute("email", email);
                            err = "Incorrect password, retry";
                            view += "&msg=" + msg + "&err=" + err + "&title=" + title + "&email=" + email;
                            try {
                                res.sendRedirect(view);
                            } catch (IOException ex) {
                                System.out.println("Voter Logout Fail to redirect" + ex.getMessage());
                            }
                        }
                    } else {
                        err = "Fail to login, please retry"; // error message should be displayed on view page
                        view += "&msg=" + msg + "&err=" + err + "&title=" + title;
                        try {
                            res.sendRedirect(view);
                        } catch (IOException ex) {
                            System.out.println("Voter Logout Fail to redirect" + ex.getMessage());
                        }
                    }
                } else {
                    err = "Invalid election link";
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election id";
                System.out.println("NFE: " + ex);
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
