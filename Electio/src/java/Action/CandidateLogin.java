package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplOrganization;
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Utilities.RandomString;
import java.sql.SQLException;
import java.text.ParseException;
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
        String view = "index.jsp";
        String title = "Login";
        String msg = null;
        String err = null;
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("") || password == null || password.equals("")) {
            err = "Insufficiant parameters, email, password and retype password required";
        } else {
            try {
                long election_id = Long.parseLong(elec_id);
                password = RandomString.encryptPassword(password);
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                if (objN.nomineeLogin(election_id, email, password)) {
                    view = "home.jsp";
                    title = "Home Page";
                    req.getSession().setAttribute("election_id", elec_id);
                    req.getSession().setAttribute("candidate_email", email);
                    Nominee n = objN.getNominee(election_id, email);
                    req.getSession().setAttribute("candidate_name", n.getFirstname());
                    Election e = objE.getElection(election_id);
                    req.setAttribute("election", e);
                    int nominee_status = objN.getNomineeStatus(election_id, email);
                    req.setAttribute("nominee_status", nominee_status);
                    System.out.println("Name: " + n.getFirstname());
                    if (nominee_status == 1) {
                        Candidate c = objC.getCandidate(election_id, email);
                        req.setAttribute("candidate", c);
                    } else if (nominee_status == 2) {
                        String reason = objN.getReason(election_id, email);
                        req.setAttribute("reason", reason);
                    }
                } else {
                    err = "Invalid login cradentials, please retry";
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election number";
                System.out.println("NFE: " + ex);
            } catch (SQLException ex) {
                err = "Could not complete action";
                System.out.println("CandidateLogin SQL Err: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
