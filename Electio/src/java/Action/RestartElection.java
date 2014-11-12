/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplElectionCommissioner;
import DAO.DBDAOImplVoter;
import Model.Election;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class RestartElection implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String elec_id = req.getParameter("election_id");
        String email = (String) req.getSession().getAttribute("email");
        String view = "index.jsp";
        String title = "Login";
        String err = null;
        String msg = null;
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            view = "dashboard.jsp";
            title = "Dashboard";
            try {
                ArrayList<Election> elections = null;
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                elections = objE.getCompletedElections(email);
                req.setAttribute("elections", elections);
                if (elec_id == null || elec_id.equals(null)) {
                    err = "Elecion id missing";
                } else {
                    long election_id = Long.parseLong(elec_id);
                    if (!objE.isValidElectionId(election_id, email)) {
                        err = "Invalid election id";
                    } else {
                        Timestamp voting_start = new Timestamp(dateFormat.parse(req.getParameter("voting_start")).getTime());
                        Timestamp voting_end = new Timestamp(dateFormat.parse(req.getParameter("voting_end")).getTime());
                        DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                        DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                        if (objE.updateElectionDates(election_id, voting_start, voting_end) && objC.setVoteZero(election_id) && objV.resetAllVoterStatus()) {
                            msg = "Election Restarted";
                        } else {
                            err = "Fail to restart election";
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                err = "Invalid election id";
                System.out.println("NFE: " + ex);
            } catch (ParseException ex) {
                err = "Invalid voting dates";
                System.out.println("Parse Err: " + ex);
            } catch (Exception ex) {
                System.out.println("Restart Election Err: " + ex.getMessage());
            }
        }
        req.setAttribute("title", title);
        req.setAttribute("err", err);
        req.setAttribute("msg", msg);
        return view;
    }
}
