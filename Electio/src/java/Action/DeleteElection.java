/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplVoter;
import DAO.DBDAOImplementation;
import Model.Election;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class DeleteElection implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String view = "index.jsp";
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String msg = null;
        String err = null;
        String title = "Login";
        if (email == null || email.equals("")) {
            err = "Session expired, please login again";
        } else {
            try {
                view = "listElections.jsp";
                title = "Elections";
                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();
                DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                if (elec_id == null || !objE.isValidElectionId(Long.parseLong(elec_id), email)) {
                    err = "Fail to locate election id, please retry";
                } else {
                    long id = Integer.parseInt(req.getParameter("id"));
                    if (objE.deleteElection(email, id) && objV.deleteVoterForElection(id) && objN.deleteNominee(email, id) && objC.deleteCandidateForElection(id) && objP.deleteProbableNomineeForElection(id) && objN.deleteRejectedNomineeForElection(id)) {
                        msg = "Election deleted successfully";
                    } else {
                        err = "Fail to delete election, please retry";
                    }
                }
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("DeleteElection Error: " + ex.getMessage());
            }
        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }
}
