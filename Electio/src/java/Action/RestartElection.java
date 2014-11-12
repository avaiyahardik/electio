/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String view = "Controller?action=dashboard";
        String title = "Dashboard";
        String err = null;
        String msg = null;
        if (email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            try {
                long election_id = Long.parseLong(elec_id);
                Timestamp voting_start = new Timestamp(dateFormat.parse(req.getParameter("voting_start")).getTime());
                Timestamp voting_end = new Timestamp(dateFormat.parse(req.getParameter("voting_end")).getTime());
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                if (objE.updateElectionDates(election_id, voting_start, voting_end) && objC.setVoteZero(election_id)) {
                    msg = "Election Restarted";
                } else {
                    err = "Fail to restart election";
                }
            } catch (ParseException ex) {
                Logger.getLogger(RestartElection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println("Restart Election Err: " + ex.getMessage());
            }
        }
        req.setAttribute("title", title);
        req.setAttribute("err", err);
        req.setAttribute("msg", msg);
        return view;
    }
}
